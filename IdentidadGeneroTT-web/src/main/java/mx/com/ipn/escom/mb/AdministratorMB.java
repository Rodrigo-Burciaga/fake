/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.ipn.escom.dao.GenericDAO;
import mx.ipn.escom.dto.AdministradorDTO;
import mx.ipn.escom.dto.RepresentanteInstitucionDTO;
import mx.ipn.escom.ejb.AdministradorEJB;
import mx.ipn.escom.ejb.InstitucionesEJB;
import mx.ipn.escom.ejb.RepresentanteInstitucionEJB;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "administratorMB")
@SessionScoped
public class AdministratorMB extends GenericMB implements Serializable {

    @EJB
    AdministradorEJB administradorEJB;
    @Inject
    CatalogMB catalogMB;
    @EJB
    InstitucionesEJB institucionesEJB;
    @EJB
    GenericDAO genericDAO;
    @EJB
    RepresentanteInstitucionEJB representanteInstitucionEJB;

    private AdministradorDTO administradorLogin = new AdministradorDTO();
    private AdministradorDTO administradorDTO = new AdministradorDTO();
    private RepresentanteInstitucionDTO representante;

    public AdministratorMB() {
    }

    public void findRepresentante() {
        representante = new RepresentanteInstitucionDTO();
        representante.getEntidad().setId(administradorDTO.getIdRepresentante());
        Respuesta<RepresentanteInstitucionDTO> respuestaDTO
                = representanteInstitucionEJB.findByID(representante);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            representante = respuestaDTO.getResultado();
            representante.setFechaInicio(
                    representante.getDf().format(
                            representante.getEntidad().getFechainicio()));
            if (representante.getEntidad().getFechafin() != null) {
                representante.setFechaFin(
                        representante.getDf().format(
                                representante.getEntidad().getFechafin()));
            }
        }
    }

    public String updateRepresentante() {
        Respuesta<RepresentanteInstitucionDTO> respuestaDTO
                = representanteInstitucionEJB.update(representante);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "updateRep.success");
            Respuesta<AdministradorDTO> respuestaAdmin
                    = administradorEJB.findById(administradorLogin);
            if (respuestaAdmin.getCodigo() == CodigoRespuesta.OK) {
                administradorLogin = respuestaAdmin.getResultado();
            }
            return NavigationConstants.REPINSTWR;
        } else {
            if (respuestaDTO.getMensaje() != null) {
                addMessage("global.error", "globalMSG",
                        FacesMessage.SEVERITY_ERROR, respuestaDTO.getMensaje());
            } else {
                addMessage("global.error", "globalMSG",
                        FacesMessage.SEVERITY_ERROR, "global.error");
            }
        }
        return null;
    }

    public String deleteRep() {
        Respuesta<RepresentanteInstitucionDTO> respuestaDTO
                = representanteInstitucionEJB.delete(representante);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            Respuesta<AdministradorDTO> respuestaAdmin
                    = administradorEJB.findById(administradorDTO);
            if (respuestaAdmin.getCodigo() == CodigoRespuesta.OK) {
                administradorDTO = respuestaAdmin.getResultado();
            }
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteRep.success");
        } else {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, respuestaDTO.getMensaje());
        }
        return NavigationConstants.REPINSTWR;
    }

    @PostConstruct
    public void init() {
        System.out.println("se está inicializando el administrador");
    }

    @Override
    public String prepareAdd() {
        administradorDTO = new AdministradorDTO();
        return NavigationConstants.ADDADMINISTRATOR;
    }

    @Override
    public String add() {
        Respuesta<AdministradorDTO> respuestaDTO
                = administradorEJB.add(administradorDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addAdministrator.success");
            catalogMB.updateAdministrators();
            return NavigationConstants.VIEWADMINISTRATORSWR;

        } else {
            if (respuestaDTO.getMensaje() != null) {
                addMessage("global.error", "globalMSG",
                        FacesMessage.SEVERITY_ERROR, respuestaDTO.getMensaje());
            }

            return null;
        }
    }

    @Override
    public String prepareUpdate() {
        return NavigationConstants.EDITPERFIL;
    }

    @Override
    public String update() {
        Respuesta<AdministradorDTO> respuestaDTO
                = administradorEJB.update(administradorLogin);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "updateAdmin.success");
            Respuesta<AdministradorDTO> respuestaAdmin
                    = administradorEJB.findById(administradorLogin);
            if (respuestaAdmin.getCodigo() == CodigoRespuesta.OK) {
                administradorLogin = respuestaAdmin.getResultado();
            }
            return NavigationConstants.DETAILADMINWR;
        } else {
            if (respuestaDTO.getMensaje() != null) {
                addMessage("global.error", "globalMSG",
                        FacesMessage.SEVERITY_ERROR, respuestaDTO.getMensaje());
            } else {
                addMessage("global.error", "globalMSG",
                        FacesMessage.SEVERITY_ERROR, "global.error");
            }
        }
        return null;
    }

    @Override
    public String prepareDelete() {
        return NavigationConstants.DELETEADMIN;
    }

    @Override
    public String delete() {
        Respuesta<AdministradorDTO> respuesta
                = administradorEJB.delete(administradorDTO);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteAdmin.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteAdmin.fail");
        }
        return NavigationConstants.VIEWADMINISTRATORSWR;
    }

    @Override
    public String viewDetail() {
        administradorDTO.setFechaInicio(administradorDTO.getDf().format(
                administradorDTO.getEntidad().getFechainicio()));
        if (administradorDTO.getEntidad().getFechafin() != null) {
            administradorDTO.setFechaFin(administradorDTO.getDf().format(
                    administradorDTO.getEntidad().getFechainicio()));
        }
        return NavigationConstants.DETAILADMIN;
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        return NavigationConstants.VIEWADMINISTRATORS;
    }

    @Override
    public String find() {
        Respuesta<AdministradorDTO> respuesta
                = administradorEJB.findById(administradorDTO);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            administradorDTO = respuesta.getResultado();
        }
        return null;
    }

    public AdministradorDTO getAdministradorDTO() {
        return administradorDTO;
    }

    public void setAdministradorDTO(AdministradorDTO administradorDTO) {
        this.administradorDTO = administradorDTO;
    }

    public RepresentanteInstitucionDTO getRepresentante() {
        return representante;
    }

    public void setRepresentante(RepresentanteInstitucionDTO representante) {
        this.representante = representante;
    }

    public AdministradorDTO getAdministradorLogin() {
        return administradorLogin;
    }

    public void setAdministradorLogin(AdministradorDTO administradorLogin) {
        this.administradorLogin = administradorLogin;
    }
    
    

}
