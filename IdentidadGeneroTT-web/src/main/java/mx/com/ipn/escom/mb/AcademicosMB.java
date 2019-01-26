/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.ipn.escom.dto.AcademicoDTO;
import mx.ipn.escom.ejb.AcademicoEJB;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "academicosMB")
@SessionScoped
public class AcademicosMB extends GenericMB implements Serializable {

    @EJB
    AcademicoEJB academicoEJB;
    private AcademicoDTO academicoDTO;

    public AcademicosMB() {
    }

    @PostConstruct
    public void init() {
        academicoDTO = new AcademicoDTO();
    }

    public String backDetail() {
        return NavigationConstants.DETAILPERFILACAD;
    }

    @Override
    public String prepareAdd() {
        return NavigationConstants.ADDACADEMICO;
    }

    @Override
    public String add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String prepareUpdate() {
        return NavigationConstants.EDITACADEMICO;
    }

    @Override
    public String update() {
        Respuesta<AcademicoDTO> respuestaDTO
                = academicoEJB.update(academicoDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "updateAcad.success");
            Respuesta<AcademicoDTO> respuestaAcad
                    = academicoEJB.findByID(academicoDTO);
            if (respuestaAcad.getCodigo() == CodigoRespuesta.OK) {
                academicoDTO = respuestaAcad.getResultado();
            }
            return NavigationConstants.DETAILPERFILACADWR;
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
        return NavigationConstants.DELETEACADEMICO;
    }

    @Override
    public String delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String viewDetail() {
        return NavigationConstants.VIEWACADEMICOS;
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        return NavigationConstants.VIEWACADEMICOS;
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AcademicoDTO getAcademicoDTO() {
        return academicoDTO;
    }

    public void setAcademicoDTO(AcademicoDTO academicoDTO) {
        this.academicoDTO = academicoDTO;
    }

}
