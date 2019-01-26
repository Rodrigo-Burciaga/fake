/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.com.ipn.escom.identidadGenero.util.SessionUtils;
import mx.ipn.escom.dto.AcademicoDTO;
import mx.ipn.escom.dto.AdministradorDTO;
import mx.ipn.escom.dto.LoginDTO;
import mx.ipn.escom.dto.RepresentanteInstitucionDTO;
import mx.ipn.escom.ejb.AcademicoEJB;
import mx.ipn.escom.ejb.AdministradorEJB;
import mx.ipn.escom.ejb.LoginEJB;
import mx.ipn.escom.ejb.RepresentanteInstitucionEJB;
import mx.ipn.escom.modelo.Academico;
import mx.ipn.escom.modelo.Administrador;
import mx.ipn.escom.modelo.RepresentanteInstitucion;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "loginMB")
@SessionScoped
public class LoginMB extends GenericMB implements Serializable {

    @EJB
    LoginEJB loginEJB;
    @EJB
    AdministradorEJB administradorEJB;
    @EJB
    RepresentanteInstitucionEJB representanteInstitucionEJB;
    @EJB
    AcademicoEJB academicoEJB;
    @Inject
    AdministratorMB administratorMB;
    @Inject
    RepresentanteMB representanteMB;
    @Inject
    AcademicosMB academicosMB;

    private LoginDTO loginDTO = new LoginDTO();

    public LoginMB() {
    }

    public String validateAccess() {
        Respuesta<LoginDTO> respuestaDTO = loginEJB.validateAccess(loginDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            loginDTO = respuestaDTO.getResultado();
            loginDTO.setLogged(true);
            HttpSession sesion = SessionUtils.getSession();
            sesion.setAttribute("user", loginDTO.getEntidad());
            switch (respuestaDTO.getResultado().getTipoUser()) {
                case 1:
                    System.out.println("entrando como administrador");
                    Respuesta<AdministradorDTO> respuestaAdmin
                            = administradorEJB.findById(new AdministradorDTO(
                                    (Administrador) respuestaDTO
                                            .getResultado().getEntidad()));
                    administratorMB.getAdministradorLogin().setEntidad(
                            respuestaAdmin.getResultado().getEntidad());
                    return NavigationConstants.INADMIN;
                case 2:
                    System.out.println("entrando como representante");
                    Respuesta<RepresentanteInstitucionDTO> respuestaRep
                            = representanteInstitucionEJB.findByID(
                                    new RepresentanteInstitucionDTO(
                                            (RepresentanteInstitucion) respuestaDTO
                                                    .getResultado().getEntidad()));
                    representanteMB.getRepresentanteInstitucionDTO()
                            .setEntidad(respuestaRep.getResultado().getEntidad());
                    return NavigationConstants.INREP;
                case 3:
                    System.out.println("entrando como academico");
                    Respuesta<AcademicoDTO> respuestaAcad
                            = academicoEJB.findByID(
                                    new AcademicoDTO((Academico) respuestaDTO
                                            .getResultado().getEntidad()));
                    academicosMB.getAcademicoDTO().setEntidad(respuestaAcad
                            .getResultado().getEntidad());
                    return NavigationConstants.INACADEMIC;
            }

        }
        if (respuestaDTO.getMensaje() != null) {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_ERROR, respuestaDTO.getMensaje());
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_ERROR, "global.failLogin");
        }
        return NavigationConstants.LOGINWR;
    }

    public void formatDate() {
        if (loginDTO.getEntidad() instanceof Administrador) {
            loginDTO.setFechaInicio(loginDTO.getFt()
                    .format(((Administrador) loginDTO.getEntidad())
                            .getFechainicio()));
            ((Administrador) loginDTO.getEntidad()).getFechainicio();
            if (((Administrador) loginDTO.getEntidad()).getFechafin() != null) {
                loginDTO.setFechaFin(loginDTO.getFt()
                        .format(((Administrador) loginDTO.getEntidad())
                                .getFechafin()));
            }
        } else if (loginDTO.getEntidad() instanceof RepresentanteInstitucion) {
            loginDTO.setFechaInicio(loginDTO.getFt()
                    .format(((RepresentanteInstitucion) loginDTO.getEntidad())
                            .getFechainicio()));
            if (((RepresentanteInstitucion) loginDTO.getEntidad()).getFechafin() != null) {
                loginDTO.setFechaFin(loginDTO.getFt()
                        .format(((RepresentanteInstitucion) loginDTO.getEntidad())
                                .getFechafin()));
            }
        } else {
            loginDTO.setFechaInicio(loginDTO.getFt()
                    .format(((Academico) loginDTO.getEntidad())
                            .getFechainicio()));
            if (((Academico) loginDTO.getEntidad()).getFechafin() != null) {
                loginDTO.setFechaFin(loginDTO.getFt()
                        .format(((Academico) loginDTO.getEntidad())
                                .getFechafin()));
            }
        }
    }

    @Override
    public String prepareAdd() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareUpdate() {
        return NavigationConstants.EDITPERFIL;
    }

    @Override
    public String update() {
        return null;
    }

    @Override
    public String prepareDelete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String viewDetail() {
        return NavigationConstants.EDITPERFIL;
    }

    @Override
    public String clear() {
        loginDTO = new LoginDTO();
        return null;
    }

    @Override
    public String back() {
        switch (loginDTO.getTipoUser()) {
            case 1:
                return NavigationConstants.navigate(NavigationConstants.PERFIL1);
            case 2:
                return NavigationConstants.navigate(NavigationConstants.PERFIL2);
            case 3:
                return NavigationConstants.navigate(NavigationConstants.PERFIL3);
        }
        return null;
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }
}
