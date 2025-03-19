package be.hogent.bappoc.shared;

public enum ActivityToTaskMapping {
    REGISTRATIE_SOLLICITANT("REGISTRATIE SOLLICITANT", "Bezorg Uitnodiging aan sollicitant."),
    CONTRACT_MAKEN("CONTRACT MAKEN","Maak contract aan."),
    CONTRACT_BEZORGEN("CONTRACT BEZORGEN", "Stuur contract naar klant voor ondertekening."),
    SOLLICTANT_NAAR_MEDEWERKER("SOLLICITANT NAAR MEDEWERKER", "Zet sollicitant om naar medewerker"),
    REGISTRATIE_DIMONA("REGISTRATIE DIMONA","Registreer Dimona."),
    AFHANDELING_CONTRACT("AFHANDELING CONTRACT","Verwerk contract."),
    TELLERS("TELLERS","Maak tellers aan."),
    DOELGROEPEN("DOELGROEPEN","Registreer doelgroepen."),
    MAALTIJDCHEQUES("MAALTIJDCHEQUES","Vraag maaltijdcheques aan."),
    ASR("ASR", "Dien aangifte ASR in."),
    FEEDBACK("FEEDBACK","Bezorg feedback aan klant en sollicitant.");

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
