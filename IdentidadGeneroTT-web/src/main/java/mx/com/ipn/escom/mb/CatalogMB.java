/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.ipn.escom.dto.AdministradorDTO;
import mx.ipn.escom.dto.CategoriaDTO;
import mx.ipn.escom.dto.CuestionariosDTO;
import mx.ipn.escom.dto.InstitutionDTO;
import mx.ipn.escom.dto.RepresentanteInstitucionDTO;
import mx.ipn.escom.dto.SectionDTO;
import mx.ipn.escom.dto.TipoDTO;
import mx.ipn.escom.ejb.AdministradorEJB;
import mx.ipn.escom.ejb.CategoriasEJB;
import mx.ipn.escom.ejb.InstitucionesEJB;
import mx.ipn.escom.ejb.QuestionariesEJB;
import mx.ipn.escom.ejb.RepresentanteInstitucionEJB;
import mx.ipn.escom.ejb.SectionsEJB;
import mx.ipn.escom.ejb.TipoEJB;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "catalogMB")
@ApplicationScoped
public class CatalogMB extends GenericMB {

    @EJB
    InstitucionesEJB institucionesEJB;
    @EJB
    CategoriasEJB categoriasEJB;
    @EJB
    SectionsEJB sectionsEJB;
    @EJB
    TipoEJB tipoEJB;
    @EJB
    QuestionariesEJB questionariesEJB;
    @EJB
    RepresentanteInstitucionEJB representanteInstitucionEJB;
    @EJB
    AdministradorEJB administradorEJB;

    private List<CategoriaDTO> categorias;
    private List<SectionDTO> secciones;
    private List<TipoDTO> tipos;
    private List<AdministradorDTO> administradores;

    public CatalogMB() {
    }

    @PostConstruct
    public void init() {
        Respuesta<CategoriaDTO> respuestaCatDTO
                = categoriasEJB.findAll();
        if (respuestaCatDTO.getCodigo() == CodigoRespuesta.OK) {
            categorias = respuestaCatDTO.getResultados();
        }
        Respuesta<SectionDTO> respuestaSeccionDTO
                = sectionsEJB.findAll();
        if (respuestaSeccionDTO.getCodigo() == CodigoRespuesta.OK) {
            secciones = respuestaSeccionDTO.getResultados();
        }

        Respuesta<TipoDTO> respuestaTipoDTO
                = tipoEJB.findAll();
        if (respuestaTipoDTO.getCodigo() == CodigoRespuesta.OK) {
            tipos = respuestaTipoDTO.getResultados();
        }
        
        Respuesta<AdministradorDTO> respuestaAdmins
                = administradorEJB.findAll();
        if (respuestaAdmins.getCodigo() == CodigoRespuesta.OK) {
            administradores = respuestaAdmins.getResultados();
        }

    }
    public void updateSections() {
        System.out.println("actualizando secciones");
        Respuesta<SectionDTO> respuestaSeccionDTO
                = sectionsEJB.findAll();
        if (respuestaSeccionDTO.getCodigo() == CodigoRespuesta.OK) {
            secciones = respuestaSeccionDTO.getResultados();
        }
    }

    public void updateCategories() {
        Respuesta<CategoriaDTO> respuestaCatDTO
                = categoriasEJB.findAll();
        if (respuestaCatDTO.getCodigo() == CodigoRespuesta.OK) {
            categorias = respuestaCatDTO.getResultados();
        }
    }

    public void updateAdministrators() {
        Respuesta<AdministradorDTO> respuestaAdmin
                = administradorEJB.findAll();
        if (respuestaAdmin.getCodigo() == CodigoRespuesta.OK) {
            administradores = respuestaAdmin.getResultados();
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String back() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String find() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<CategoriaDTO> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaDTO> categorias) {
        this.categorias = categorias;
    }

    public List<SectionDTO> getSecciones() {
        return secciones;
    }

    public void setSecciones(List<SectionDTO> secciones) {
        this.secciones = secciones;
    }

    public List<TipoDTO> getTipos() {
        return tipos;
    }

    public void setTipos(List<TipoDTO> tipos) {
        this.tipos = tipos;
    }
    
    public List<AdministradorDTO> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<AdministradorDTO> administradores) {
        this.administradores = administradores;
    }

}
