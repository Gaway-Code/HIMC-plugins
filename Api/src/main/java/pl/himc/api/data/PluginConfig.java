package pl.himc.api.data;

import org.diorite.cfg.annotations.CfgComment;

public final class PluginConfig {

    @CfgComment("Prefix uprawnień pluginów")
    public String prefixPermission = "himc";

    @CfgComment("Konfiguracja bazy danych")
    public database database = new database();

    public static class database {
        @CfgComment("Czy baza danych ma być włączona")
        public boolean enable = true;
        @CfgComment("Typ bazy danych: MYSQL/SQLITE")
        public String type = "MYSQL";
        @CfgComment("Po jakim czasie połączenie ma zostać zerwane gdy baza danych nie odpowiada? (w milisekundach)")
        public int timeout = 30000;
        @CfgComment("Ilość otwartych połączeń z bazą danych (0 - plugin sam otwiera optymalną ilość połączeń)")
        public int poolSize = 10;

        public mysql mysql = new mysql();
        public sqlite sqlite = new sqlite();
    }

    public static class mysql {
        @CfgComment("Host bazy danych")
        public String host = "127.0.0.1";
        @CfgComment("Port bazy danych")
        public int port = 3306;
        @CfgComment("Użytkownik bazy danych")
        public String user = "root";
        @CfgComment("Hasło bazy danych")
        public String password = "";
        @CfgComment("Nazwa bazy danych")
        public String dbName = "minecraft";
        @CfgComment("Czy połączenie ma byc szyfrowane? (Jeśli localhost = false)")
        public boolean useSSL = false;
    }
    public static class sqlite {
        @CfgComment("Nazwa pliku bazy danych")
        public String dbName = "minecraft.db";
    }
}
