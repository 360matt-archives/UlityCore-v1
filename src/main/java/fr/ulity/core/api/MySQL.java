package fr.ulity.core.api;

import fr.ulity.core.api.adapter.Connect_MySQL;
import fr.ulity.core.api.adapter.SQL;

public class MySQL {
    private static boolean canManipulate = false;

    public String table;

    public MySQL() {
        this.table = "minecraft";
        reload();
    }

    public MySQL(String table) {
        this.table = table;
        reload();
    }

    public void reload() {
        Connect_MySQL.connect();
        if (Connect_MySQL.isConnected())
            canManipulate = true;
        else
            return;

        if (table != null)
            SQL.createTable(table);

    }

    public void delete(String key) {
        fr.ulity.core.api.adapter.SQL.delete(key, table);
    }

    public boolean exist(String key) {
        return fr.ulity.core.api.adapter.SQL.exists(key, table);
    }

    public void set(String key, Object value) {
        fr.ulity.core.api.adapter.SQL.set(key, value, table);
    }

    public String getString(String key) {
        return fr.ulity.core.api.adapter.SQL.getString(key, table);
    }

    public Object getObject(String key) {
        return fr.ulity.core.api.adapter.SQL.getObject(key, table);
    }

    public int getInt(String key) {
        return fr.ulity.core.api.adapter.SQL.getInt(key, table);
    }
}
