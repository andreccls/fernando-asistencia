package Clases;
// Generated 02-may-2013 17:40:38 by Hibernate Tools 3.2.1.GA



/**
 * TareareunionId generated by hbm2java
 */
public class TareareunionId  implements java.io.Serializable {


     private int idTareareunion;
     private int idTarea;

    public TareareunionId() {
    }

    public TareareunionId(int idTareareunion, int idTarea) {
       this.idTareareunion = idTareareunion;
       this.idTarea = idTarea;
    }
   
    public int getIdTareareunion() {
        return this.idTareareunion;
    }
    
    public void setIdTareareunion(int idTareareunion) {
        this.idTareareunion = idTareareunion;
    }
    public int getIdTarea() {
        return this.idTarea;
    }
    
    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof TareareunionId) ) return false;
		 TareareunionId castOther = ( TareareunionId ) other; 
         
		 return (this.getIdTareareunion()==castOther.getIdTareareunion())
 && (this.getIdTarea()==castOther.getIdTarea());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getIdTareareunion();
         result = 37 * result + this.getIdTarea();
         return result;
   }   


}


