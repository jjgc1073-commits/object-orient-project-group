package DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseInitializer {

    // ✅ TABLAS DEFINIDAS INDIVIDUALMENTE

    private static final String USER_TABLE =
            "CREATE TABLE IF NOT EXISTS USER (\n"
                    + " user_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " name TEXT,\n"
                    + " email TEXT NOT NULL UNIQUE,\n"
                    + " password_hash TEXT NOT NULL,\n"
                    + " birth_date TEXT,\n"
                    + " age INTEGER,\n"
                    + " role TEXT NOT NULL CHECK(role IN ('STUDENT', 'TEACHER', 'ADMIN'))\n"
                    + ");";

    private static final String STUDENT_INFO_TABLE =
            "CREATE TABLE IF NOT EXISTS STUDENT_INFO (\n"
                    + " student_info_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " user_id INTEGER NOT NULL UNIQUE,\n"
                    + " level_academic TEXT,\n"
                    + " about_me TEXT,\n"
                    + " FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE\n"
                    + ");";

    private static final String TUTOR_INFO_TABLE =
            "CREATE TABLE IF NOT EXISTS TUTOR_INFO (\n"
                    + " tutor_info_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " user_id INTEGER NOT NULL UNIQUE,\n"
                    + " located_in TEXT NOT NULL,\n"
                    + " hourly_rate REAL NOT NULL,\n"
                    + " about_me TEXT,\n"
                    + " FOREIGN KEY (user_id) REFERENCES USER(user_id) ON DELETE CASCADE\n"
                    + ");";

    private static final String REVIEW_TABLE =
            "CREATE TABLE IF NOT EXISTS REVIEW (\n"
                    + " review_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " tutor_user_id INTEGER NOT NULL,\n"
                    + " student_user_id INTEGER,\n"
                    + " score INTEGER NOT NULL,\n"
                    + " comment TEXT,\n"
                    + " review_date TEXT,\n"
                    + " FOREIGN KEY (tutor_user_id) REFERENCES USER(user_id) ON DELETE CASCADE,\n"
                    + " FOREIGN KEY (student_user_id) REFERENCES USER(user_id) ON DELETE SET NULL\n"
                    + ");";

    private static final String TUTOR_SUBJECT_TABLE =
            "CREATE TABLE IF NOT EXISTS TUTOR_SUBJECT (\n"
                    + " tutor_subject_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " tutor_info_id INTEGER NOT NULL,\n"
                    + " subject TEXT NOT NULL,\n"
                    + " FOREIGN KEY(tutor_info_id) REFERENCES TUTOR_INFO(tutor_info_id) ON DELETE CASCADE\n"
                    + ");";

    private static final String TUTOR_CERTIFICATION_TABLE =
            "CREATE TABLE IF NOT EXISTS TUTOR_CERTIFICATION (\n"
                    + " certification_id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                    + " tutor_info_id INTEGER NOT NULL,\n"
                    + " certification TEXT NOT NULL,\n"
                    + " FOREIGN KEY(tutor_info_id) REFERENCES TUTOR_INFO(tutor_info_id) ON DELETE CASCADE\n"
                    + ");";


    // ✅ MÉTODO PRINCIPAL
    public static void initialize() {
        try (Connection conn = ConnectionDB.getConnection();
             Statement stmt = conn.createStatement()) {

            // ✅ ACTIVAR FOREIGN KEYS
            stmt.execute("PRAGMA foreign_keys = ON;");
            System.out.println("✅ FOREIGN KEYS activadas");

            // ✅ EJECUTAR TABLAS UNA POR UNA (CLARO Y SEGURO)
            stmt.execute(USER_TABLE);
            stmt.execute(STUDENT_INFO_TABLE);
            stmt.execute(TUTOR_INFO_TABLE);
            stmt.execute(REVIEW_TABLE);
            stmt.execute(TUTOR_SUBJECT_TABLE);
            stmt.execute(TUTOR_CERTIFICATION_TABLE);

            System.out.println("✅ Tablas creadas correctamente");

        } catch (SQLException e) {
            System.out.println("❌ Error al inicializar la base de datos: " + e.getMessage());
        }
    }
}

