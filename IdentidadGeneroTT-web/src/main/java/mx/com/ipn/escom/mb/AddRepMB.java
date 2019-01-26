/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.ipn.escom.dto.AdministradorDTO;
import mx.ipn.escom.dto.RepresentanteInstitucionDTO;
import mx.ipn.escom.ejb.AdministradorEJB;
import mx.ipn.escom.ejb.RepresentanteInstitucionEJB;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "addRepMB")
@ViewScoped
public class AddRepMB extends GenericMB implements Serializable {

    @EJB
    RepresentanteInstitucionEJB representanteInstitucionEJB;
    @Inject
    AdministratorMB administratorMB;
    @EJB
    AdministradorEJB administradorEJB;

    private RepresentanteInstitucionDTO representanteDTO;

    public AddRepMB() {
    }

    @PostConstruct
    public void init() {
        representanteDTO = new RepresentanteInstitucionDTO();
    }

    public RepresentanteInstitucionDTO getRepresentanteDTO() {
        return representanteDTO;
    }

    public void setRepresentanteDTO(RepresentanteInstitucionDTO representanteDTO) {
        this.representanteDTO = representanteDTO;
    }

    @Override
    public String prepareAdd() {
        return NavigationConstants.ADDREP;
    }

    @Override
    public String add() {
        Respuesta<RepresentanteInstitucionDTO> respuestaDTO
                = representanteInstitucionEJB.add(representanteDTO,
                        administratorMB.getAdministradorLogin());
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            Respuesta<AdministradorDTO> respuestaAdmin
                    = administradorEJB.findById(administratorMB.getAdministradorLogin());
            if (respuestaAdmin.getCodigo() == CodigoRespuesta.OK) {
                administratorMB.setAdministradorLogin(respuestaAdmin.getResultado());
                addMessage("global.success", "globalMSG",
                        FacesMessage.SEVERITY_INFO, "addRep.success");
                return NavigationConstants.REPINSTWR;
            }
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
    public String prepareUpdate() {
        return NavigationConstants.EDITREP;
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareDelete() {
        return NavigationConstants.DELETEREP;
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String viewDetail() {
        return NavigationConstants.DETAILREP;
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        return NavigationConstants.REPINST;
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
