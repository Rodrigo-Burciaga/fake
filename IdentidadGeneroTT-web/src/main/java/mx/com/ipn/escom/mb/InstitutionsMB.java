/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.ipn.escom.dto.AdministradorDTO;
import mx.ipn.escom.dto.InstitutionDTO;
import mx.ipn.escom.ejb.AdministradorEJB;
import mx.ipn.escom.ejb.InstitucionesEJB;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "institutionsMB")
@SessionScoped
public class InstitutionsMB extends GenericMB implements Serializable {

    @EJB
    private InstitucionesEJB institucionesEJB;
    @EJB
    AdministradorEJB administradorEJB;
    @Inject
    AdministratorMB administratorMB;

    private InstitutionDTO institutionDTO = new InstitutionDTO();

    public InstitutionsMB() {
    }

    @PostConstruct
    public void init() {
    }

    @Override
    public String prepareAdd() {
        institutionDTO = new InstitutionDTO();
        return NavigationConstants.ADDINSTITUTION;
    }

    @Override
    public String add() {
        AdministradorDTO admin = administratorMB.getAdministradorLogin();
        if (admin.getEntidad() != null) {
            institutionDTO.getEntidad().setIdadministrador(admin.getEntidad());
            Respuesta<InstitutionDTO> respuestaDTO = institucionesEJB.
                    save(institutionDTO);
            if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
                Respuesta<AdministradorDTO> respuestaAdminDTO
                        = administradorEJB.findById(admin);
                if (respuestaAdminDTO.getCodigo() == CodigoRespuesta.OK) {
                    administratorMB.setAdministradorLogin(respuestaAdminDTO
                            .getResultado());
                }
                addMessage("global.success", "globalMSG",
                        FacesMessage.SEVERITY_INFO, "addInstitution.success");
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", respuestaDTO.getMensaje()));
            }
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_ERROR, "global.error");
        }
        return NavigationConstants.VIEWINSTITUTIONSWR;
    }

    @Override
    public String prepareUpdate() {
        Respuesta<InstitutionDTO> respuestaDTO
                = institucionesEJB.findById(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            institutionDTO.setEntidad(respuestaDTO.getResultado().getEntidad());
        }
        return NavigationConstants.EDITINSTITUTION;
    }

    @Override
    public String update() {
        Respuesta<InstitutionDTO> respuestaDTO = institucionesEJB.
                update(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "editInstitition.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "editInstitution.error");
        }
        return NavigationConstants.DETAILINSTITUCIONWR;
    }

    @Override
    public String prepareDelete() {
        Respuesta<InstitutionDTO> respuestaDTO
                = institucionesEJB.findById(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            institutionDTO.setEntidad(respuestaDTO.getResultado().getEntidad());
        }
        return NavigationConstants.DELETEINSTITUTION;
    }

    @Override
    public String delete() {
        Respuesta<InstitutionDTO> respuestaDTO
                = institucionesEJB.delete(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            Respuesta<AdministradorDTO> respuestaAdmin
                    = administradorEJB.findById(administratorMB
                            .getAdministradorLogin());
            if (respuestaAdmin.getCodigo() == CodigoRespuesta.OK) {
                administratorMB.setAdministradorLogin(respuestaAdmin
                        .getResultado());
            }
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteInstitution.success");

        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteInstitution.error");
        }
        return NavigationConstants.VIEWINSTITUTIONSWR;
    }

    @Override
    public String viewDetail() {
        Respuesta<InstitutionDTO> respuestaDTO
                = institucionesEJB.findById(institutionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            institutionDTO.setEntidad(respuestaDTO.getResultado().getEntidad());
        }
        return NavigationConstants.DETAILINSTITUCION;
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        return NavigationConstants.VIEWINSTITUTIONS;
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public InstitutionDTO getInstitutionDTO() {
        return institutionDTO;
    }

    public void setInstitutionDTO(InstitutionDTO institutionDTO) {
        this.institutionDTO = institutionDTO;
    }

}
