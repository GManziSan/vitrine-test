# language: pt

Funcionalidade: Categorias

  Cenário: Criar uma categoria
    Dado uma categoria básica
    Quando chamar a api com post para o endpoint "/categories"
    Então deve criar uma categoria

  Cenário: Toda categoria deve ter nome único
    Dado uma categoria com nome já existente
    Quando chamar a api com post para o endpoint "/categories"
    Então não deve criar uma categoria

  Esquema do Cenario: Nomes de categoria podem ter caracteres especias
    Dado uma categoria com <caracteres>
    Quando chamar a api com post para o endpoint "/categories"
    Então deve criar uma categoria com <caracteres>

    Exemplos:
      |caracteres|
      |" "       |
      |"-.;"     |

  Esquema do Cenário: O alias da categoria não deve conter espaço e nem caracteres especiais
    Dado um alias com <caracteres>
    Quando chamar a api com post para o endpoint "/categories"
    Então nao deve criar a categoria com <caracteres>

    Exemplos:
      |caracteres|
      |" "       |
      |"-.;"     |

  Cenário: Não deve criar categoria sem nome
    Dado uma categoria que não contém nome
    Quando chamar a api com post para o endpoint "/categories"
    Então não deve criar uma categoria

  Cenário: Não deve criar categoria sem alias
    Dado Uma categoria que não contém alias
    Quando chamar a api com post para o endpoint "/categories"
    Então não deve criar uma categoria


