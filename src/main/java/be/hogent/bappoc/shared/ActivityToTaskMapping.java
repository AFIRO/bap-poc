package be.hogent.bappoc.shared;

public enum ActivityToTaskMapping {
    FILL_OUT_FORM("FILL OUT FORM", "FILL OUT FORM");

    private final String activityReference;
    private final String taskReference;

    ActivityToTaskMapping(String activityReference, String taskReference){
        this.activityReference = activityReference;
        this.taskReference = taskReference;
    }

    public static String getTaskReference(String activityReference) {
        for (ActivityToTaskMapping mapping : values()) {
            if (mapping.activityReference.equals(activityReference)) {
                return mapping.taskReference;
            }
        }
        return null;
    }

    public static boolean containsActivityReference(String activityReference) {
        for (ActivityToTaskMapping mapping : values()) {
            if (mapping.activityReference.equals(activityReference)) {
                return true;
            }
        }
        return false;
    }
}
