package com.example.practicalfive;

/**
 * A plain helper class that validates and normalises the name of a Chinese
 * municipality entered by the user. It is deliberately kept independent of
 * the Android UI so that the same rules can be reused by every Activity.
 */
public final class TextProcessor {

    private TextProcessor() {
        // Utility class: no instances required.
    }

    /**
     * Validates a municipality name and returns its correctly capitalised form.
     *
     * @param municipality the raw text entered by the user
     * @return "Beijing", "Shanghai", "Tianjin" or "Chongqing" for valid input,
     *         or {@code null} when the input is null, empty, whitespace only,
     *         or not one of the four supported municipalities
     */
    public static String processMunicipality(String municipality) {
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        switch (municipality.trim().toLowerCase()) {
            case "beijing":
                return "Beijing";
            case "shanghai":
                return "Shanghai";
            case "tianjin":
                return "Tianjin";
            case "chongqing":
                return "Chongqing";
            default:
                return null;
        }
    }
}
