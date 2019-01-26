/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.ipn.escom.dto.CuestionariosDTO;
import mx.ipn.escom.dto.OpcionRespuestaDTO;
import mx.ipn.escom.ejb.QuestionariesEJB;
import mx.ipn.escom.ejb.RespuestaEJB;
import mx.ipn.escom.modelo.Categoria;
import mx.ipn.escom.modelo.OpcionRespuesta;
import mx.ipn.escom.modelo.Pregunta;
import mx.ipn.escom.modelo.Seccion;
import mx.ipn.escom.util.CodigoRespuesta;
import mx.ipn.escom.util.Respuesta;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "chartView")
@SessionScoped
public class ChartView extends GenericMB implements Serializable {

    @EJB
    QuestionariesEJB questionariesEJB;
    @EJB
    RespuestaEJB respuestaEJB;

    private CuestionariosDTO cuestionarioDTO;

    private List<BarChartModel> animatedModels;

    @PostConstruct
    public void init() {
        System.out.println("mx.com.ipn.escom.mb.ChartView.init()");
        cuestionarioDTO = new CuestionariosDTO();
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
        Respuesta<CuestionariosDTO> respuestaDTO
                = questionariesEJB.findById(cuestionarioDTO);
        if (respuestaDTO.getCodigo() == CodigoRespuesta.OK) {
            animatedModels = new ArrayList<>();
            cuestionarioDTO = respuestaDTO.getResultado();
            for (Categoria categoria : cuestionarioDTO.getEntidad().getCategoriaList()) {
                for (Seccion seccion : categoria.getSeccionList()) {
                    for (Pregunta pregunta : seccion.getPreguntaList()) {
                        BarChartModel model = new BarChartModel();
                        List<ChartSeries> charts = new ArrayList<>();
                        List<Long> numeros = new ArrayList<>();
                        OpcionRespuestaDTO opcionDTO = new OpcionRespuestaDTO();
                        for (OpcionRespuesta opcionRespuesta
                                : pregunta.getOpcionRespuestaList()) {
                            opcionDTO.setEntidad(opcionRespuesta);
                            Respuesta<Long> respuesta
                                    = respuestaEJB.countRespuestas(opcionDTO);
                            numeros.add(respuesta.getResultado());
//                            System.out.println("poniendo en el set "
//                                    + opcionRespuesta.getOpcion()
//                                    + " " + respuesta.getResultado());
                            ChartSeries chart = new ChartSeries();
                            chart.setLabel(opcionRespuesta.getOpcion());
                            chart.set(pregunta.getPregunta(),
                                    respuesta.getResultado());
                            charts.add(chart);
                        }
                        for (ChartSeries chart : charts) {
                            model.addSeries(chart);
                        }
                        model.setAnimate(true);
                        model.setLegendPosition("ne");
                        Axis yAxis = model.getAxis(AxisType.Y);
                        yAxis.setMin(0);
                        Long l = Collections.max(numeros);
                        yAxis.setMax(l);
                        animatedModels.add(model);
                    }
                }
            }
        }
        return null;
    }

    public CuestionariosDTO getCuestionarioDTO() {
        return cuestionarioDTO;
    }

    public void setCuestionarioDTO(CuestionariosDTO cuestionarioDTO) {
        this.cuestionarioDTO = cuestionarioDTO;
    }

    public QuestionariesEJB getQuestionariesEJB() {
        return questionariesEJB;
    }

    public void setQuestionariesEJB(QuestionariesEJB questionariesEJB) {
        this.questionariesEJB = questionariesEJB;
    }

    public List<BarChartModel> getAnimatedModels() {
        return animatedModels;
    }

    public void setAnimatedModels(List<BarChartModel> animatedModels) {
        this.animatedModels = animatedModels;
    }
    

}
