🧪 OrangeHRM – QA Automation Framework

1️⃣ Introducción

El presente informe describe el desarrollo de un framework de automatización de pruebas E2E para la aplicación OrangeHRM, utilizando Java, Selenium WebDriver, Cucumber y TestNG, aplicando el enfoque BDD y el patrón Page Object Model.

2️⃣ Alcance de la automatización
Validar flujos críticos del sistema como:
•	Login
•	Navegación
•	Búsqueda de empleados
•	Logout
•	Flujo completo End-to-End
________________________________________
3️⃣ Herramientas y tecnologías utilizadas

| Herramienta | Uso |
|------------|-----|
| Java | Lenguaje base |
| Selenium WebDriver | Automatización UI |
| Cucumber | Definición de escenarios BDD |
| Gherkin | Lenguaje de negocio |
| TestNG | Ejecución y control de tests |
| Maven | Gestión de dependencias |
| IntelliJ IDEA | IDE |
| Git / GitHub | Control de versiones |
________________________________________
4️⃣ Estructura del proyecto

```text
src
├── main
│   └── java
│       └── com.ximeq.pages
│           ├── BasePage.java
│           ├── LoginPage.java
│           ├── DashboardPage.java
│           └── PIMPage.java
│
└── test
    ├── java
    │   └── com.ximeq.steps
    │       ├── Hooks.java
    │       └── OrangeHRMSteps.java
    │
    └── resources
        ├── features
        │   └── flujo_completo_e2e.feature
        └── runner
            └── TestRunner.java
pom.xml
testng.xml
README.md
________________________________________
5️⃣ Patrón de Diseño del framework
🔹Page Object Model (POM)
•	Cada página del sistema tiene su propia clase 
•	Los locators y métodos están encapsulados
•	Los Steps solo orquestan el flujo, no contienen lógica de UI
________________________________________
🔹 BasePage
BasePage centraliza:
•	WebDriver
•	WebDriverWait
•	Métodos reutilizables:
o	click()
o	type()
o	getText()
o	isElementVisible()
o	waitForElementToDisappear()
Esto evita duplicación de código y mejora el mantenimiento del framework ante cambios de UI.
________________________________________
6️⃣ BDD y definición de escenarios
✔️ Login exitoso
@login
Scenario: Login exitoso
  Given estoy en la página de OrangeHRM
  When ingreso el usuario "Admin"
  And ingreso la contraseña "admin123"
  And el usuario hace click en "Login"
  Then debería ver la página Dashboard
________________________________________
✔️ Búsqueda de empleado (PIM)
@pim
Scenario: Buscar empleado
  Given estoy en la página de OrangeHRM
  And inicio sesión con credenciales válidas
  When navego al módulo PIM
  And busco al empleado con nombre "John"
  Then debería ver los resultados de la búsqueda
________________________________________
✔️ Flujo End-to-End
@e2e
Scenario: Flujo completo E2E
  Given estoy en la página de OrangeHRM
  And inicio sesión con credenciales válidas
  When navego al módulo PIM
  And busco al empleado con nombre "John"
  Then debería ver los resultados de la búsqueda
  And hago logout
  Then debería ver la página de login
________________________________________
✅ Validaciones implementadas
•	Verificación de páginas por encabezados (Dashboard, PIM)
•	Validación de resultados de búsqueda
•	Validación de logout exitoso
•	Validación negativa de error de login inesperado
Ejemplo:
Assert.assertFalse(loginPage.isErrorDisplayed(),
        "Se mostró un error de login inesperado");
________________________________________
7️⃣ Manejo de esperas
•	Esperas explícitas centralizadas en BasePage
•	Uso de:
o	isElementVisible
o	waitForElementToDisappear
•	Evita Thread.sleep()
•	Reduce flakiness en ejecución
________________________________________
8️⃣ Ejecución de tests
Cloná el repositorio y guardalo en una carpeta local.
- Desde consola
Ejecutar todos los tests:
mvn clean test

- Desde el IDE
Ejecutar la clase **TestRunner**

- O ejecutar escenarios individuales utilizando tags:
mvn clean test -Dcucumber.filter.tags="@login"
mvn clean test -Dcucumber.filter.tags="@pim"
mvn clean test -Dcucumber.filter.tags="@e2e"

📊 Informe de ejecución

El proyecto genera automáticamente un informe de ejecución en formato HTML utilizando **Cucumber Reports**.

📍 Ubicación del reporte
El archivo se genera en la siguiente ruta luego de ejecutar los tests:

target/cucumber-report.html

▶️ Cómo visualizarlo
1. Ejecutar los tests desde el runner o con Maven.
2. Abrir el archivo `cucumber-report.html` ubicado en la carpeta `target`.
3. El reporte se abre en el navegador mostrando:
   - Features ejecutadas
   - Scenarios
   - Steps
   - Estado de cada ejecución (Passed / Failed)
   - Detalle de errores en caso de fallos

ℹ️ Nota
La carpeta `target` no se versiona en el repositorio ya que es generada automáticamente en cada ejecución.
________________________________________
9️⃣ Buenas prácticas aplicadas
•	BDD claro y legible
•	Separación de responsabilidades (Pages, Steps, Runner)
•	Código reutilizable mediante Page Object Model (POM)
•	Las assertions principales se ubican en pasos Then (resultado esperado)
•	Se utilizan validaciones adicionales en Given y When para asegurar precondiciones y evitar falsos positivos durante la ejecución del flujo
•	Sin lógica de negocio en los Steps
•	Framework escalable y mantenible
________________________________________
👤 Autor
QA Automation: Quintana María Jimena
