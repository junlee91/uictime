package com.sodastudio.uictime.utils;

/**
 * Created by Jun on 7/22/2017.
 */

public class CourseLibrary {

    public int getTermValue(String term){
        if(term.equals("Fall 2017"))
            return 220178;

        return 0;
    }

    public String getTermString(int termID){
        if(termID == 220178)
            return "Fall 2017";

        return "";
    }

    public String getSubjectValue(String subject){

        switch (subject){
            case "Academic Skills Program": return "ASP";
            case "Accounting": return "ACTG";
            case "African American Studies": return "AAST";
            case "Anatomy and Cell Biology": return "ANAT";
            case "Anthropology": return "ANTH";
            case "Applied Health Sciences": return "AHS";
            case "Arabic": return "ARAB";
            case "Archaeological Studies": return "ARST";
            case "Architecture": return "ARCH";
            case "Art": return "ART";
            case "Art History": return "AH";
            case "Basic Medical Sciences": return "BMS";
            case "Biochem Molecular Genetics": return "BCMG";
            case "Bioengineering": return "BIOE";
            case "Biological Sciences": return "BIOS";
            case "Biomedical Hlth Info Sciences": return "BHIS";
            case "Biomedical Visualization": return "BVIS";
            case "Biopharmaceutical Sciences": return "BPS";
            case "Biostatistics": return "BSTT";
            case "Business Administration": return "BA";
            case "Campus Courses": return "CC";
            case "Catholic Studies": return "CST";
            case "Central East European Studies": return "CEES";
            case "Chemical Engineering": return "CHE";
            case "Chemistry": return "CHEM";
            case "Chinese": return "CHIN";
            case "Civil Materials Engineering": return "CME";
            case "Classics": return "CL";
            case "Clerkship - Medicine": return "CLER";
            case "Clerkship Electives-Chicago":return "CELE";
            case "Clerkship Electives-Peoria": return "PELE";
            case "Clerkship Electives-Rockford": return "RELE";
            case "Clerkship Electives-Urbana": return "UELE";
            case "Communication": return "COMM";
            case "Community Health Sciences": return "CHSC";
            case "Computer Science":return "CS";
            case "Criminology, Law, and Justice": return "CLJ";
            case "Curriculum and Instruction": return "CI";
            case "Dent-Applied Oral and Behav Sci": return "DAOB";
            case "Dent-Biomedical and Clinical Sci": return "DBCS";
            case "Dent-Community Learning Exper": return "DCLE";
            case "Dent-Oral/Systemic Issues": return "DOSI";
            case "Dent-Oral/Systemic Topics": return "DOST";
            case "Dental Administration": return "DADM";
            case "Dental Behavioral Science": return "DBSC";
            case "Design": return "DES";
            case "Dialogue": return "DLG";
            case "Disability and Human Development": return "DHD";
            case "Earth Environmental Sciences": return "EAES";
            case "Economics": return "ECON";
            case "Education": return "ED";
            case "Educational Policy Studies": return "EDPS";
            case "Educational Psychology": return "EPSY";
            case "Electrical and Computer Engr": return "ECE";
            case "Endodontics": return "ENDO";
            case "Energy Engineering": return "ENER";
            case "Engineering": return "ENGR";
            case "English": return "ENGL";
            case "Entrepreneurship": return "ENTR";
            case "Environmtl and Occuptnl Hlth Sci": return "EOHS";
            case "Epidemiology": return "EPID";
            case "Finance": return "FIN";
            case "French": return "FR";
            case "Gender and Women's Studies": return "GWS";
            case "Geography": return "GEOG";
            case "Germanic Studies": return "GER";
            case "Global Asian Studies": return "GLAS";
            case "Graduate College": return "GC";
            case "Graduate Educ Medical Sciences": return "GEMS";
            case "Greek, Ancient": return "GKA";
            case "Greek, Modern": return "GKM";
            case "Guaranteed Admissions Medicine": return "GAMD";
            case "Health Information Management": return "HIM";
            case "Health Policy and Administration": return "HPA";
            case "Healthy Living Practitioner": return "HLP";
            case "Hebrew": return "HEB";
            case "Histology": return "HSTL";
            case "History": return "HIST";
            case "Honors College": return "HON";
            case "Humanities": return "HUM";
            case "Industrial Engineering": return "IE";
            case "Information and Decision Sciences": return "IDS";
            case "Information Technology": return "IT";
            case "Interdisc Public Hlth Sciences": return "IPHS";
            case "Interdisc Studies in the Arts": return "ISA";
            case "International Studies": return "INST";
            case "Italian": return "ITAL";
            case "Japanese": return "JPN";
            case "Jewish Studies": return "JST";
            case "Kinesiology": return "KN";
            case "Korean": return "KOR";
            case "Latin": return "LAT";
            case "Latin American and Latino Studies": return "LALS";
            case "Learning Sciences": return "LRSC";
            case "Liberal Arts and Sciences": return "LAS";
            case "Library and Information Science": return "LIB";
            case "Linguistics": return "LING";
            case "Literatures, Cultrl Stdy and Ling": return "LCSL";
            case "Lithuanian": return "LITH";
            case "Management": return "MGMT";
            case "Marketing": return "MKTG";
            case "Master of Business Admin": return "MBA";
            case "Master of Engineering": return "MENG";
            case "Mathematical Computer Science": return "MCS";
            case "Mathematics": return "MATH";
            case "Mathematics Teaching": return "MTHT";
            case "Mechanical Engineering": return "ME";
            case "Medical Biotechnology": return "MBT";
            case "Medical Education": return "MHPE";
            case "Medical Humanities": return "MHUM";
            case "Medicinal Chem and Pharmacognosy": return "PMMP";
            case "Medicinal Chemistry": return "MDCH";
            case "Microbiology and Immunology": return "MIM";
            case "Military Science": return "MILS";
            case "Moving Image Arts": return "MOVI";
            case "Music": return "MUS";
            case "Native American Studies": return "NAST";
            case "Natural Sciences": return "NATS";
            case "Naval Science": return "NS";
            case "Neuroscience": return "NEUS";
            case "Nursing Core": return "NURS";
            case "Nursing Elective": return "NUEL";
            case "Nursing Practicum": return "NUPR";
            case "Nursing Specialty": return "NUSP";
            case "Occupational Therapy": return "OT";
            case "Oral Medicine and Diagnostic Sci": return "OMDS";
            case "Oral Sciences": return "OSCI";
            case "Oral and Maxillofacial Surgery": return "OSUR";
            case "Orthodontics": return "ORTD";
            case "Pathology": return "PATH";
            case "Patient Safety Leadership": return "PSL";
            case "Pediatric Dentistry": return "PEDD";
            case "Periodontics": return "PERI";
            case "Pharmacognosy": return "PMPG";
            case "Pharmacology": return "PCOL";
            case "Pharmacy": return "PHAR";
            case "Pharmacy Practice": return "PMPR";
            case "Pharmacy Syst,Outcomes and Policy": return "PSOP";
            case "Philosophy": return "PHIL";
            case "Physical Therapy": return "PT";
            case "Physics": return "PHYS";
            case "Physiology and Biophysics": return "PHYB";
            case "Polish": return "POL";
            case "Political Science": return "POLS";
            case "Portuguese": return "PORT";
            case "Preclinical Medicine": return "PRCL";
            case "Prosthodontics": return "PROS";
            case "Psychology": return "PSCH";
            case "Public Administration": return "PA";
            case "Public Health": return "PUBH";
            case "Public Policy Analysis": return "PPA";
            case "Religious Studies": return "RELS";
            case "Restorative Dentistry": return "REST";
            case "Russian": return "RUSS";
            case "Slavic and Baltic Languages and Lit": return "SLAV";
            case "Social Justice": return "SJ";
            case "Social Work": return "SOCW";
            case "Sociology": return "SOC";
            case "Spanish": return "SPAN";
            case "Special Education": return "SPED";
            case "Specialty Medicine": return "SPEC";
            case "Statistics": return "STAT";
            case "Surgery": return "SURG";
            case "Theatre": return "THTR";
            case "Urban Planning and Policy": return "UPP";
            case "Urban Studies": return "US";
            case "Urban and Public Affairs": return "UPA";


        }

        return null;
    }
}
