Feature: Login no SauceDemo

  Scenario: Login válido
    Given que estou na página de login
    When faço login com usuário válido
    Then devo acessar a página de inventário

  Scenario: Login inválido
    Given que estou na página de login
    When faço login com usuário inválido
    Then devo ver uma mensagem de erro