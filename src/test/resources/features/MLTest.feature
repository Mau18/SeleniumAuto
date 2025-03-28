@ML
Feature: Test Mercado Libre busqueda de productos
    Para navegar en mercado libre
    Sin haber logueado
    Validar escenarios

Rule: The user can to navigate, realize searching and add a product to cart

    Background: Navigate to Mercado Libre
    Given The user navigate to www.mercadolibre.com.mx

@Compra
Scenario: Como cliente cuando busco por Alexa, yo quiero ver si la tercera opción de la segunda pagina esta disponible y agregarla al carrito
    And busca por Producto
    And Seleccionar pagina 2
    And selecciona el tercer item
    Then el usuario es capaz de agregarlo al carrito


@valida
Scenario: Como usuario, yo puedo ver todos las categorias del listado
    Then El usuario verifica que el número de categorías es 22

@Vehiculos
Scenario Outline: Seleccionar un tipo de vehiculo y validar la cantidad de resultados
    Then El usuario selecciona la categoría Vehiculos
    And El usuario selecciona <categoria> y busca resultados
    And el usuario valida la cantidad de resultados obtenidos es mayor a 0
    
     Examples:
        |categoria| 
        |Motos    |
        |Camiones |

@images
Scenario: I can realize hoverover at all images of a product
    Then The user write Alexa and search results
    And The user select the first result
    And The user made hoverOver in the images

@list
Scenario: The user can write and obtain results in the searching
    When The user write Playstation and search results
    Then The user obtain a list of results

    
