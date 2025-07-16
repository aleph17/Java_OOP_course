public class Mechanic{
    private String name;
    private int phone;

    public Mechanic(String name){
        this.name = name;
    }

    public static class Certification{
        
        private String certName;
        private int certYear;

        public void Certification(String certName, int certYear){
            this.certName = certName;
            this.certYear = certYear;
        }

        public void printCert(){
            System.out.println(certName+" is given in "+certYear);
        }
    }
    
    public class Report {
        String log;

        public Report(String log){
            this.log = log;
        }

        public void printReport() {
            System.out.println(log +"\n given by"+ name);
        }

        public Report generateReport(String log){
            Report newReport = new Report(log);
            newReport.printReport();
            newReport.log("all good").log("proceed please");
            return newReport;
        }

        public Report log(String newInfo){
            this.log = this.log + "\n" + newInfo;
            return this;                            // for fluency you return 'this' object
        }
    }
    public void repair(){
        System.out.println(name +"is repairing your bike");
    }
}