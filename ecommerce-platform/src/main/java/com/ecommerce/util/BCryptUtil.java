package com.ecommerce.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for hashing and verifying passwords using BCrypt.
 */
public class BCryptUtil {

    /**
     * Hashes a plaintext password using BCrypt.
     *
     * @param password The plaintext password to be hashed.
     * @return A hashed version of the password.
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    /**
     * Verifies if the provided plaintext password matches the hashed password.
     *
     * @param password       The plaintext password to verify.
     * @param hashedPassword The hashed password to compare against.
     * @return true if the password matches, false otherwise.
     */
    public static boolean checkPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
