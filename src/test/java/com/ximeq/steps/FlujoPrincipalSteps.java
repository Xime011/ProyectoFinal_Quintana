package com.ximeq.steps;

import com.ximeq.hooks.Hooks;
import io.cucumber.java.en.*;
import org.testng.Assert;
import com.ximeq.pages.DashboardPage;
import com.ximeq.pages.LoginPage;
import com.ximeq.pages.PIMPage;

public class FlujoPrincipalSteps {

    private final LoginPage loginPage = new LoginPage(Hooks.driver);
    private final DashboardPage dashboardPage = new DashboardPage(Hooks.driver);
    private final PIMPage pimPage = new PIMPage(Hooks.driver);

    // ---------- GIVEN ----------
    @Given("estoy en la página de OrangeHRM")
    public void estoy_en_la_pagina_de_orangehrm() {
        loginPage.goTo();
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "No se cargó la página de login de OrangeHRM"
        );
    }

    @Given("inicio sesión con credenciales válidas")
    public void inicio_sesion_con_credenciales_validas() {
        loginPage.loginAs("Admin", "admin123");
        Assert.assertTrue(dashboardPage.isOnDashboard(),
                "No se pudo iniciar sesión correctamente"
        );
    }

    // ---------- WHEN ----------
    @When("ingreso el usuario {string}")
    public void ingreso_el_usuario(String usuario) {
        loginPage.enterUserName(usuario);
    }

    @When("ingreso la contraseña {string}")
    public void ingreso_la_contraseña(String password) {
        loginPage.enterPassword(password);
    }

    @When("el usuario hace click en {string}")
    public void el_usuario_hace_click_en_login(String boton) {
        loginPage.clickLogin();
    }

    @When("navego al módulo PIM")
    public void navego_al_modulo_pim() {
        dashboardPage.goToPIM();
        Assert.assertTrue(pimPage.isOnPIMPage(),
                "No se navegó correctamente al módulo PIM"
        );
    }

    @When("busco al empleado con nombre {string}")
    public void busco_al_empleado_con_nombre(String nombre) {
        pimPage.searchEmployeeByName(nombre);
    }

    @When("hago logout")
    public void hago_logout() {
        dashboardPage.logout();
    }

    // ---------- THEN ----------
    @Then("debería ver la página Dashboard")
    public void deberia_ver_la_pagina_dashboard() {
        Assert.assertTrue(dashboardPage.isOnDashboard(),
                "No se muestra el Dashboard después del login"
        );
        String header = dashboardPage.getHeaderText();
        Assert.assertTrue(header.contains("Dashboard"),
                "El encabezado del Dashboard no es el esperado"
        );
        Assert.assertFalse(loginPage.isErrorDisplayed(),
                "Se mostró un error de login inesperado");
    }

    @Then("debería ver los resultados de la búsqueda")
    public void deberia_ver_los_resultados_de_la_busqueda() {
        Assert.assertTrue(pimPage.hasResults());
    }

    @Then("debería ver la página de login")
    public void deberia_ver_la_pagina_de_login() {
        Assert.assertTrue(loginPage.isOnLoginPage(),
                "No se volvió a la página de login después del logout"
        );
    }
}