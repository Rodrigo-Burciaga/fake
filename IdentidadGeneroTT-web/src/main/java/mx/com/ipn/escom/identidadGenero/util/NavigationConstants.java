/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.ipn.escom.identidadGenero.util;

/**
 *
 * @author andii-burciaga
 */
public class NavigationConstants {

    /**
     * IDS For menu LeftBar for the navigation
     */
    public final static String INSTITUTIONS = "INSTITUTIONS";
    public final static String ADMINISTRATORS = "ADMINISTRATORS";
    public final static String INDEX = "INDEX";
    public final static String LOGIN = "LOGIN";
    public final static String REDIRECT = "?faces-redirect=true";
    public final static String PERFIL = "PERFIL";
    public final static String PERFIL1 = "PERFIL1";
    public final static String PERFIL2 = "PERFIL2";
    public final static String PERFIL3 = "PERFIL3";
    public final static String ACADEMICOS = "ACADEMICOS";
    public final static String CUESTIONARIOS = "CUESTIONARIOS";
    public final static String REPINSTITUCION = "REPINSTITUCION";
    public final static String CSESION = "CSESION";
    public final static String ESTADISTICAS = "ESTADISTICAS";

    /**
     * Navigation for institutions
     */
    public final static String ADDINSTITUTION = "/administrator/addInstitution" + REDIRECT;
    public final static String VIEWADMINISTRATORS = "/administrator/viewAdministrators" + REDIRECT;
    public final static String VIEWADMINISTRATORSWR = "/administrator/viewAdministrators";
    public final static String VIEWINSTITUTIONS = "/administrator/viewInstitutions" + REDIRECT;
    public final static String VIEWINSTITUTIONSWR = "/administrator/viewInstitutions";
    public final static String EDITINSTITUTION = "/administrator/editInstitution" + REDIRECT;
    public final static String DELETEINSTITUTION = "deleteInstitution";
    public final static String DETAILINSTITUCION = "/administrator/detailInstitution" + REDIRECT;
    public final static String DETAILINSTITUCIONWR = "/administrator/detailInstitution";
    public final static String VIEWACADEMICOS = "/InstitutionRepresentative/viewAcademicos" + REDIRECT;
    public final static String VIEWCUESTIONARIOS = "/InstitutionRepresentative/viewQuestionaries" + REDIRECT;
    public final static String VIEWCUESTIONARIOSWR = "/InstitutionRepresentative/viewQuestionaries";
    public final static String EDITACADEMICO = "/Academic/editPerfil";
    public final static String DELETEACADEMICO = "deleteAcademico";
    public final static String ADDACADEMICO = "addAcademico";
    public final static String ADDCUESTIONARIO = "/InstitutionRepresentative/addQuestionary" + REDIRECT;
    public final static String ADDCUESTIONARIOWR = "/InstitutionRepresentative/addQuestionary";
    public final static String EDITCUESTIONARIO = "/InstitutionRepresentative/editCuestionario" + REDIRECT;
    public final static String DELETECUESTIONARIO = "/InstitutionRepresentative/deleteCuestionario" + REDIRECT;
    public final static String VIEWSTATISTICS = "/Academic/viewStatistics";
    public final static String TOLOGIN = "/login" + REDIRECT;
    public final static String LOGINWR = "/login";
    public final static String TOINDEX = "/index.xhtml" + REDIRECT;
    public final static String INADMIN = "/administrator/indexAdmin" + REDIRECT;
    public final static String TOPERFIL1 = "/administrator/detailPerfil" + REDIRECT;
    public final static String TOPERFIL2 = "/InstitutionRepresentative/detailPerfil" + REDIRECT;
    public final static String TOPERFIL3 = "/Academic/detailPerfil" + REDIRECT;
    public final static String TOPERFILWR = "/administrator/detailPerfil";
    public final static String EDITPERFIL = "/administrator/editPerfil" + REDIRECT;
    public final static String EDITPERFILWR = "/administrator/editPerfil";
    public final static String DETAILQUESTIONARY = "/InstitutionRepresentative/detailQuestionary" + REDIRECT;
    public final static String INREP = "/InstitutionRepresentative/indexRep" + REDIRECT;
    public final static String REPINST = "/administrator/viewRepresentatives" + REDIRECT;
    public final static String REPINSTWR = "/administrator/viewRepresentatives";
    public final static String INACADEMIC = "/Academic/indexAcademic" + REDIRECT;
    public final static String ADDADMINISTRATOR = "/administrator/addAdministrator" + REDIRECT;
    public final static String DETAILADMIN = "/administrator/detailAdministrator" + REDIRECT;
    public final static String DETAILADMINWR = "/administrator/detailAdministrator";
    public final static String DELETEADMIN = "/administrator/deleteAdministrator" + REDIRECT;
    public final static String ADDREP = "/administrator/addRepresentative" + REDIRECT;
    public final static String DETAILREP = "/administrator/detailRepresentative" + REDIRECT;
    public final static String EDITREP = "/administrator/editRepresentative" + REDIRECT;
    public final static String DELETEREP = "/administrator/deleteRepresentative" + REDIRECT;
    public final static String EDITREPREP = "/InstitutionRepresentative/editPerfil" + REDIRECT;
    public final static String DETAILPERFILREP = "/InstitutionRepresentative/detailPerfil" + REDIRECT;
    public final static String DETAILPERFILREPWR = "/InstitutionRepresentative/detailPerfil";
    public final static String DETAILPERFILACAD = "/Academic/detailPerfil" + REDIRECT;
    public final static String DETAILPERFILACADWR = "/Academic/detailPerfil";

    public enum MenuNav {
        INSTITUTIONS(NavigationConstants.VIEWINSTITUTIONS),
        ADMINISTRATORS(NavigationConstants.VIEWADMINISTRATORS),
        ACADEMICOS(NavigationConstants.VIEWACADEMICOS),
        LOGIN(NavigationConstants.TOLOGIN),
        INDEX(NavigationConstants.TOINDEX),
        CUESTIONARIOS(NavigationConstants.VIEWCUESTIONARIOS),
        PERFIL1(NavigationConstants.TOPERFIL1),
        PERFIL2(NavigationConstants.TOPERFIL2),
        PERFIL3(NavigationConstants.TOPERFIL3),
        REPINSTITUCION(NavigationConstants.REPINST),
        CSESION(NavigationConstants.TOINDEX),
        ESTADISTICAS(NavigationConstants.VIEWSTATISTICS);

        private final String navigation;

        MenuNav(String navigation) {
            this.navigation = navigation;
        }

        public String getNavRule() {
            return navigation;
        }
    }

    public static String navigate(String caseTo) {
        switch (caseTo) {
            case INSTITUTIONS:
                return MenuNav.INSTITUTIONS.getNavRule();
            case ADMINISTRATORS:
                return MenuNav.ADMINISTRATORS.getNavRule();
            case INDEX:
                return MenuNav.INDEX.getNavRule();
            case LOGIN:
                return MenuNav.LOGIN.getNavRule();
            case PERFIL1:
                return MenuNav.PERFIL1.getNavRule();
            case PERFIL2:
                return MenuNav.PERFIL2.getNavRule();
            case PERFIL3:
                return MenuNav.PERFIL3.getNavRule();
            case ACADEMICOS:
                return MenuNav.ACADEMICOS.getNavRule();
            case CUESTIONARIOS:
                return MenuNav.CUESTIONARIOS.getNavRule();
            case REPINSTITUCION:
                return MenuNav.REPINSTITUCION.getNavRule();
            case CSESION:
                return MenuNav.CSESION.getNavRule();
            case ESTADISTICAS:
                return MenuNav.ESTADISTICAS.getNavRule();
            default:
                return "/index.xhtml?faces-redirect=true";
        }
    }

}
