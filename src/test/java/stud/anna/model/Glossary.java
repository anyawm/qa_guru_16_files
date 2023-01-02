package stud.anna.model;

import com.google.gson.annotations.SerializedName;

public class Glossary {
    public String title;
    @SerializedName("GlossDiv")
    public GlossDiv glossDiv;

    public static class GlossDiv {
        public String title;
        public boolean flag;

    }
}
