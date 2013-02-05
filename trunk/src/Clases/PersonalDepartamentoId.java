package Clases;
// Generated 31/10/2012 10:25:04 by Hibernate Tools 3.2.1.GA



/**
 * PersonalDepartamentoId generated by hbm2java
 */
public class PersonalDepartamentoId  implements java.io.Serializable {


     private int idPersonal;
     private int idDepartamento;

    public PersonalDepartamentoId() {
    }

    public PersonalDepartamentoId(int idPersonal, int idDepartamento) {
       this.idPersonal = idPersonal;
       this.idDepartamento = idDepartamento;
    }
   
    public int getIdPersonal() {
        return this.idPersonal;
    }
    
    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }
    public int getIdDepartamento() {
        return this.idDepartamento;
    }
    
    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof PersonalDepartamentoId) ) return false;
		 PersonalDepartamentoId castOther = ( PersonalDepartamentoId ) other; 
         
		 return (this.getIdPersonal()==castOther.getIdPersonal())
 && (this.getIdDepartamento()==castOther.getIdDepartamento());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdPersonal();
         result = 37 * result + this.getIdDepartamento();
         return result;
   }   


}


