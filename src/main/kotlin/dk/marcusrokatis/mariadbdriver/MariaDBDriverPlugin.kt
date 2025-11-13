package dk.marcusrokatis.mariadbdriver

import org.bukkit.plugin.java.JavaPlugin

class MariaDBDriverPlugin : JavaPlugin() {

    override fun onLoad() {
        try {
            Class.forName("org.mariadb.jdbc.Driver")
            logger.info("MariaDB JDBC driver registered globally.")
        } catch (t: Throwable) {
            logger.severe("Failed to register MariaDB JDBC driver: ${t.message}")
        }
    }

    override fun onEnable() {
        // Plugin startup logic
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
