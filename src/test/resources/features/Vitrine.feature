# language: pt

Funcionalidade: Categorias

  Cenário: Criar uma categoria
    Dado uma categoria básica
    Quando chamar a api com post para o endpoint "/categories"
    Então deve criar uma categoria

#  Cenário: Toda categoria deve ter nome único
#    Quando criar uma categoria
#    E o nome for igual a outra categoria
#    Então deve negar a criação
#
#  Cenário: Nomes de categoria podem ter caracteres especias
#    Quando criar uma categoria
#    E conter " "
#    E conter "."
#    Então deve criar a categoria
#
#  Cenário: O alias da categoria não deve conter espaço e nem caracteres especiais
#    Quando criar uma categoria
#    E o alias conter " "
#    Então nao deve criar a categoria
#
#  Cenário: Toda categoria deve ter nome e alias
#    Quando criar uma categoria
#    E não conter "nome"
#    E não conter "alias"
#    Então não deve criar a categoria




