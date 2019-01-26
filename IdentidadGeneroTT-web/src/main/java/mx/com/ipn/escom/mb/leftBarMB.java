package mx.com.ipn.escom.mb;

import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import mx.com.ipn.escom.identidadGenero.util.GenericMB;
import mx.com.ipn.escom.identidadGenero.util.NavigationConstants;

/**
 *
 * @author andii-burciaga
 */
@Named(value = "leftBarMB")
@ApplicationScoped
public class leftBarMB extends GenericMB implements Serializable {

    @Inject
    LoginMB loginMB;

    private String actualMenu;

    public leftBarMB() {
    }

    @PostConstruct
    public void init() {
        actualMenu = "INDEX";
    }

    /**
     *
     * @param id ID del menu a cambiar el estado
     * @return
     */
    public String handleNavigation(String id) {
        setActualMenu(id);
        if (loginMB.getLoginDTO().getTipoUser() != null 
                && id.equals(NavigationConstants.PERFIL)) {
            switch (loginMB.getLoginDTO().getTipoUser()) {
                case 1:
                    return NavigationConstants.navigate(NavigationConstants.PERFIL1);
                case 2:
                    return NavigationConstants.navigate(NavigationConstants.PERFIL2);
                case 3:
                    return NavigationConstants.navigate(NavigationConstants.PERFIL3);
            }
        }
        return NavigationConstants.navigate(id);
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

    public String getActualMenu() {
        return actualMenu;
    }

    public void setActualMenu(String actualMenu) {
        this.actualMenu = actualMenu;
    }

}
