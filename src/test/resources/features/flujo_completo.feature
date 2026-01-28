Feature: Flujo completo en OrangeHRM
  Como usuario del sistema
  Quiero realizar distintas acciones en OrangeHRM
  Para validar el funcionamiento del login, la búsqueda y el flujo completo

  @login
  Scenario: Login exitoso
    Given estoy en la página de OrangeHRM
    When ingreso el usuario "Admin"
    And ingreso la contraseña "admin123"
    And el usuario hace click en "Login"
    Then debería ver la página Dashboard

  @pim
  Scenario: Buscar empleado
    Given estoy en la página de OrangeHRM
    And inicio sesión con credenciales válidas
    When navego al módulo PIM
    And busco al empleado con nombre "John"
    Then debería ver los resultados de la búsqueda

  @e2e
  Scenario: Flujo completo E2E
    Given estoy en la página de OrangeHRM
    And inicio sesión con credenciales válidas
    When navego al módulo PIM
    And busco al empleado con nombre "John"
    Then debería ver los resultados de la búsqueda
    And hago logout
    And debería ver la página de login
