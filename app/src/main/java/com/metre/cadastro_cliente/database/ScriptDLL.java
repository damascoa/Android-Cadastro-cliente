package com.metre.cadastro_cliente.database;

public class ScriptDLL {

    public static String getCreateTableCliente(){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE CLIENTE ( \n" +
                "        CODIGO    INTEGER PRIMARY KEY NOT NULL,\n" +
                "        NOME      VARCHAR (250) NOT NULL DEFAULT '',\n" +
                "        ENDERECO  VARCHAR (255) NOT NULL DEFAULT '',\n" +
                "        EMAIL     VARCHAR (200) NOT NULL DEFAULT '',\n" +
                "        TELEFONE  VARCHAR (20)  NOT NULL DEFAULT '')");
        return sql.toString();
    }
}
