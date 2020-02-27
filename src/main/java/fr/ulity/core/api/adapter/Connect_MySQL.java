// 
// Decompiled by Procyon v0.5.36
//
// j'ai repris le code de " vagdedes "
// en décompilant cette ressource: https://www.spigotmc.org/resources/mysql-api.23932/
// 

package fr.ulity.core.api.adapter;

import java.sql.ResultSet;
import java.sql.Statement;

import fr.ulity.core.api.Api;
import fr.ulity.core.api.Config;
import fr.ulity.core.api.Lang;

import java.sql.DriverManager;
import java.sql.Connection;

public class Connect_MySQL {
    private static Connection con;

    public static Connection getConnection() {
        return Connect_MySQL.con;
    }

    public static void setConnection(final String host, final String user, final String password, final String database, final String port, final String ssl) {
        if (host == null || user == null || password == null || database == null)
            return;
        disconnect(false);
        try {
            Connect_MySQL.con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true&useSSL=" + ssl, user, password);
            System.out.println(MultiLang.getExp("connected"));
        } catch (Exception e) {
            System.out.println(MultiLang.getExp("connect_error") + " " + e.getMessage());
        }
    }

    public static void connect() {
        connect(true);
    }

    private static void connect(final boolean message) {

        Config mySQL_cfg = new Config("mysql");

        String host = mySQL_cfg.getOrSetDefault("host", "localhost");
        String port = mySQL_cfg.getOrSetDefault("port", "3600");
        String user = mySQL_cfg.getOrSetDefault("user", "root");
        String database = mySQL_cfg.getOrSetDefault("database", "mc_network");
        String password = mySQL_cfg.getOrSetDefault("password", "secret");
        String ssl = mySQL_cfg.getOrSetDefault("use-ssl", "true");

        if (isConnected()) {
            if (message)
                System.out.println(MultiLang.getExp("already_connected"));
        } else if (host.equalsIgnoreCase(""))
            System.out.println(MultiLang.getExp("host_blank"));
        else if (user.equalsIgnoreCase(""))
            System.out.println(MultiLang.getExp("user_blank"));
        else if (password.equalsIgnoreCase(""))
            System.out.println(MultiLang.getExp("password_blank"));
        else if (database.equalsIgnoreCase(""))
            System.out.println(MultiLang.getExp("database_blank"));
        else if (port.equalsIgnoreCase(""))
            System.out.println(MultiLang.getExp("port_blank"));
        else if (ssl.equalsIgnoreCase(""))
            System.out.println(MultiLang.getExp("ssl_blank"));
        else
            setConnection(host, user, password, database, port, ssl);
    }

    public static void disconnect() {
        disconnect(true);
    }

    private static void disconnect(final boolean message) {
        try {
            if (isConnected()) {
                Connect_MySQL.con.close();
                if (message)
                    System.out.println(MultiLang.getExp("disconnected"));
            }
        } catch (Exception e) {
            if (message)
                System.out.println(MultiLang.getExp("disconnect_error") + " " + e.getMessage());
        }
        Connect_MySQL.con = null;
    }

    public static void reconnect() {
        disconnect();
        connect();
    }

    public static boolean isConnected() {
        if (Connect_MySQL.con != null) {
            try {
                return !Connect_MySQL.con.isClosed();
            } catch (Exception e) {
                System.out.println(MultiLang.getExp("mysql_connection"));
                System.out.println(MultiLang.getExp("error") + " " + e.getMessage());
            }
        }
        return false;
    }

    public static boolean update(final String command) {
        if (command == null)
            return false;
        boolean result = false;
        connect(false);
        try {
            final Statement st = getConnection().createStatement();
            st.executeUpdate(command);
            st.close();
            result = true;
        } catch (Exception e) {
            System.out.println("§cMySQL Update:");
            System.out.println(MultiLang.getExp("command") + " " + command);
            System.out.println(MultiLang.getExp("error") + " " + e.getMessage());
        }
        disconnect(false);
        return result;
    }

    public static ResultSet query(final String command) {
        if (command == null) {
            return null;
        }
        connect(false);
        ResultSet rs = null;
        try {
            final Statement st = getConnection().createStatement();
            rs = st.executeQuery(command);
        } catch (Exception e) {
            System.out.println("§cMySQL Query:");
            System.out.println(MultiLang.getExp("command") + " " + command);
            System.out.println(MultiLang.getExp("error") + " " + e.getMessage());
        }
        return rs;
    }
}

class MultiLang {
    public static String getExp(String name) {
        String exp = "";

        try {
            if (Lang.lang.equals("fr"))
                exp = FR.class.getField(name).get(FR.class).toString();
            else
                exp = EN.class.getField(name).get(EN.class).toString();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        exp = "§e" + Api.name + ": " + exp;

        if (Api.type.equals("bukkit"))
            exp = org.bukkit.ChatColor.translateAlternateColorCodes('§', exp);
        else
            exp = net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('§', exp);

        return exp;
    }
}

class FR {
    public static String connected = "§aMySQL connecté";
    public static String connect_error = "§cErreur de connexion:";
    public static String already_connected = "§cErreur connexion MySQL: Déjà connecté !";
    public static String disconnected = "§aMySQL déconnecté.";
    public static String disconnect_error = "§cErreur déconnexion MySQL:";
    public static String mysql_connection = "§cConnexion MySQL:";
    public static String error = "§cErreur:";
    public static String command = "§cCommande:";
    public static String port_blank = "§cErreur de configuration: le port est vide";
    public static String database_blank = "§cErreur de configuration: le nom de base de donnée est vide";
    public static String host_blank = "§cErreur de configuration: l'hôte est vide";
    public static String user_blank = "§cErreur de configuration: l'utilisateur est vide";
    public static String password_blank = "§cErreur de configuration: le mot de passe est vide";
    public static String ssl_blank = "§cErreur de configuration: le ssl est vide";
}

class EN {
    public static String connected = "§aMySQL connected";
    public static String connect_error = "§cConnect Error:";
    public static String already_connected = "§cMySQL Connect Error: Already connected";
    public static String disconnected = "§aMySQL disconnected.";
    public static String disconnect_error = "§cMySQL Disconnect Error:";
    public static String mysql_connection = "§cMySQL Connection:";
    public static String error = "§cError:";
    public static String command = "§cCommand:";
    public static String port_blank = "§cConfig Error: Port is blank";
    public static String database_blank = "§cConfig Error: Database is blank";
    public static String host_blank = "§cConfig Error: Host is blank";
    public static String user_blank = "§cConfig Error: User is blank";
    public static String password_blank = "§cConfig Error: Password is blank";
    public static String ssl_blank = "§cConfig Error: SSL is blank";

}