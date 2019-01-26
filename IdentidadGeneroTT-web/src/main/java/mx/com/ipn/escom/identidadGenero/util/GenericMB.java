/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.identidadGenero.util;

import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author andii-burciaga
 */
public abstract class GenericMB {

    public ResourceBundle getResourceBundle() {
        ResourceBundle bundle
                = FacesContext.getCurrentInstance().getApplication().getResourceBundle(
                        FacesContext.getCurrentInstance(), "msgUI");
        return bundle;
    }

    public String getMessage(String key) {
        return getResourceBundle().getString(key);
    }

    public String getMessage(String key, Object... parameters) {
        String message = getResourceBundle().getString(key);
        return MessageFormat.format(message, parameters);
    }

    public FacesMessage getFacesMessage(String key) {
        return new FacesMessage(getMessage(key));
    }

    public void addMessage(String key, String componentId, Object... params) {
        FacesContext.getCurrentInstance().addMessage(componentId,
                new FacesMessage(getMessage(key)));
    }

    public void addMessage(String key, String componentId,
            FacesMessage.Severity severity, String detail, Object... params) {
        FacesContext.getCurrentInstance().addMessage(componentId,
                new FacesMessage(severity, getMessage(key),
                        prepareMessage(getMessage(detail), params)));

    }

    private String prepareMessage(String message, Object... params) {
        return MessageFormat.format(message, params);
    }

    public abstract String prepareAdd();

    public abstract String add();

    public abstract String prepareUpdate();

    public abstract String update();

    public abstract String prepareDelete();

    public abstract String delete();

    public abstract String viewDetail();

    public abstract String clear();

    public abstract String back();

    public abstract String find();

}
