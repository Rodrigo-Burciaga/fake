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
import javax.inject.Inject;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;
import mx.ipn.escom.dto.CuestionariosDTO;
import mx.ipn.escom.dto.RepresentanteInstitucionDTO;
import mx.ipn.escom.ejb.QuestionariesEJB;
import mx.ipn.escom.ejb.RepresentanteInstitucionEJB;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "representanteMB")
@SessionScoped
public class RepresentanteMB extends GenericMB implements Serializable {

    @Inject
    CatalogMB catalogMB;
    @EJB
    RepresentanteInstitucionEJB representanteInstitucionEJB;
    @EJB
    QuestionariesEJB questionariesEJB;
    private RepresentanteInstitucionDTO representanteInstitucionDTO;

    public RepresentanteMB() {
    }

    @PostConstruct
    public void init() {
        representanteInstitucionDTO = new RepresentanteInstitucionDTO();
    }

    public void findQuestionary() {
        representanteInstitucionDTO.getCuestionario()
                .setId(representanteInstitucionDTO.getIdCuestionario());
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.findById(
                        new CuestionariosDTO(
                                representanteInstitucionDTO.getCuestionario()));
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            representanteInstitucionDTO.setCuestionario(
                    respuestaDTO.getResultado().getEntidad());
            representanteInstitucionDTO.getCuestionariosDTO()
                    .setEntidad(respuestaDTO.getResultado().getEntidad());
        }
    }

    public String updateQuestionary() {
        return NavigationConstants.EDITCUESTIONARIO;
    }

    public String backQuestionaries() {
        return NavigationConstants.VIEWCUESTIONARIOS;
    }

    public void addCategory() {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.addCategory(representanteInstitucionDTO
                        .getCuestionariosDTO(),
                        getMessage("addQuestionary.newCategory"),
                        getMessage("addQuestionary.newSection"),
                        getMessage("editQuestion.newQuestion"),
                        getMessage("editAnswer.newOption"));
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addCategory.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addCategory.error");
        }
    }

    public void removeCategory(Long idCategory, String nombreCategoria) {
        Respuesta<CuestionariosDTO> respuesta
                = questionariesEJB.removeCategory(
                        representanteInstitucionDTO.getCuestionariosDTO(), idCategory,
                        nombreCategoria);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteCategory.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_ERROR, "deleteCategory.fail");
        }
    }

    public void addSection(Long idCategoy, String nameCategory) {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.addSection(representanteInstitucionDTO
                        .getCuestionariosDTO(), idCategoy,
                        getMessage("addQuestionary.newSection"),
                        nameCategory);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addSection.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addSection.fail1");
        }
    }

    public void removeSection(Long idCategoria, Long idSection,
            String nameSection, String nameCategory) {
        Respuesta<CuestionariosDTO> respuesta
                = questionariesEJB.removeSection(representanteInstitucionDTO
                        .getCuestionariosDTO(), idCategoria,
                        idSection, nameSection, nameCategory);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteSection.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteSection.fail");
        }
    }

    public void addQuestion(Long idCategory, Long idSection, String nameCategory,
            String nameSection) {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.addQuestion(representanteInstitucionDTO
                        .getCuestionariosDTO(), idCategory, idSection,
                        nameCategory, nameSection,
                        getMessage("editQuestion.newQuestion"));
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addQuestion.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addQuestion.fail");
        }
    }

    public void removeQuestion(Long idCategory, Long idSection, Long idQuestion,
            String nameSection, String nameCategory, String nameQuestion) {
        Respuesta<CuestionariosDTO> respuesta
                = questionariesEJB.removeQuestion(representanteInstitucionDTO
                        .getCuestionariosDTO(), idCategory,
                        idSection, idQuestion, nameSection,
                        nameCategory, nameQuestion);
        if (respuesta.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteQuestion.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_ERROR, "deleteQuestion.fail");
        }
    }

    public void addOption(Long idCategory, Long idSection, Long idQuestion,
            String nameCategory, String nameSection, String nameQuestion) {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.addOption(representanteInstitucionDTO
                        .getCuestionariosDTO(), idCategory, idSection, idQuestion,
                        nameCategory, nameSection, nameQuestion,
                        getMessage("editAnswer.newOption"));
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addOption.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "addOption.fail");
        }
    }

    public Respuesta<CuestionariosDTO> removeOption(Long idCategory, Long idSection, Long idQuestion,
            Long idOption, String nameSection, String nameCategory,
            String nameQuestion, String nameOption) {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.removeOption(representanteInstitucionDTO
                        .getCuestionariosDTO(), idCategory, idSection,
                        idQuestion, idOption, nameSection, nameCategory,
                        nameQuestion, nameOption);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteOption.success");
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteOption.fail");
        }
        return respuestaDTO;
    }

    public String probarGenero(Object t) {
        if (t.equals(1L)) {
            return getMessage("detailQuestionary.pM");
        } else {
            return getMessage("detailQuestionary.pF");
        }

    }

    public String updateCuestionario() {
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.updateQuestionary(representanteInstitucionDTO
                        .getCuestionariosDTO());
        if (respuestaDTO.getCodigo() == CodigoRespuesta.ERROR) {
            if (representanteInstitucionDTO.getCuestionariosDTO()
                    .getCaseFail() != null) {
                addMessage("global.error", "globalMSG",
                        FacesMessage.SEVERITY_ERROR,
                        respuestaDTO.getMensaje(),
                        representanteInstitucionDTO.getCuestionariosDTO()
                                .getCaseFail());
            } else {
                addMessage("global.error", "globalMSG",
                        FacesMessage.SEVERITY_ERROR,
                        respuestaDTO.getMensaje());
            }
            return null;
        } else {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "editQuestionary.success");
            representanteInstitucionDTO.setCuestionariosDTO(
                    new CuestionariosDTO());
            catalogMB.updateCategories();
            catalogMB.updateSections();
            return NavigationConstants.VIEWCUESTIONARIOSWR;
        }
    }

    public String prepareDeleteQuestionary() {
        return NavigationConstants.DELETECUESTIONARIO;
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
        return NavigationConstants.EDITREPREP;
    }

    @Override
    public String update() {
        Respuesta<RepresentanteInstitucionDTO> respuestaDTO
                = representanteInstitucionEJB.update(representanteInstitucionDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "updateRep.success");
            Respuesta<RepresentanteInstitucionDTO> respuestaRep
                    = representanteInstitucionEJB.findByID(representanteInstitucionDTO);
            if (respuestaRep.getCodigo() == CodigoRespuesta.OK) {
                representanteInstitucionDTO = respuestaRep.getResultado();
            }
            return NavigationConstants.DETAILPERFILREPWR;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete() {
        Respuesta<CuestionariosDTO> respuestaDTO = questionariesEJB
                .delete(representanteInstitucionDTO.getCuestionariosDTO());
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            addMessage("global.success", "globalMSG",
                    FacesMessage.SEVERITY_INFO, "deleteQuestionary.success");
            catalogMB.updateSections();
            catalogMB.updateCategories();
            Respuesta<RepresentanteInstitucionDTO> respuesta
                    = representanteInstitucionEJB.findByID(representanteInstitucionDTO);
            if(respuesta.getCodigo() == CodigoRespuesta.OK){
                representanteInstitucionDTO = respuesta.getResultado();
            }
        } else {
            addMessage("global.error", "globalMSG",
                    FacesMessage.SEVERITY_ERROR, "deleteQuestionary.fail");
        }
        return NavigationConstants.VIEWCUESTIONARIOSWR;
    }

    @Override
    public String viewDetail() {
        return NavigationConstants.DETAILQUESTIONARY;
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        return NavigationConstants.DETAILPERFILREP;
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public RepresentanteInstitucionDTO getRepresentanteInstitucionDTO() {
        return representanteInstitucionDTO;
    }

    public void setRepresentanteInstitucionDTO(RepresentanteInstitucionDTO representanteInstitucionDTO) {
        this.representanteInstitucionDTO = representanteInstitucionDTO;
    }

}
