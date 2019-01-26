/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.mb;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import mx.com.ipn.escom.war.mb.ValidatorPage;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "validatorsFieldsMB")
@ApplicationScoped
public class ValidatorsFieldsMB {

    private ValidatorPage.PagesToValidate login = ValidatorPage.PagesToValidate.LOGIN;
    
    public ValidatorsFieldsMB() {
        
    }

    public ValidatorPage.PagesToValidate getLogin() {
        return login;
    }

    public void setLogin(ValidatorPage.PagesToValidate login) {
        this.login = login;
    }

    
    
    
}
