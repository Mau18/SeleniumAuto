@ML
Feature: Test Mercado Libre busqueda de productos
    Para navegar en mercado libre
    Sin haber logueado
    Validar escenarios

@Compra
Scenario: Como cliente cuando busco por Alexa, yo quiero ver si la tercera opción de la segunda pagina esta disponible y agregarla al carrito
    Given el usuario navega a mercadolibre.com.mx
    And busca por Producto
    And Seleccionar pagina 2
    And selecciona el tercer item
    Then el usuario es capaz de agregarlo al carrito


@valida
Scenario: "Como usuario, yo puedo ver todos las categorias del listado"
    Given El usuario navega a mercado libre
    Then El usuario verifica que el número de categorías es 21

@Vehiculos
Scenario Outline: Seleccionar un tipo de vehiculo y validar la cantidad de resultados
    Given The user navigate to ML
    Then El usuario selecciona la categoría Vehiculos
    And El usuario selecciona <categoria> y busca resultados
    And el usuario valida la cantidad de resultados obtenidos es mayor a 0
    
     Examples:
        |categoria| 
        |Motos    |
        |Camiones |

    
    
