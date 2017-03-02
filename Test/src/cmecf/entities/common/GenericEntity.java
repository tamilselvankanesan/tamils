// Reviewer: jpd
package cmecf.entities.common;

public class GenericEntity { 

  static final String cvsInformation = "$Header: /usr/local/cvsroot/nextgen/entities/src/main/java/cmecf/entities/common/GenericEntity.java,v 4.10 2014/11/06 19:17:46 jpd Exp $";

  private boolean updatedEntity = false;

  public static final int CMECF_BATCH_SIZE = 400;

  public boolean isUpdatedEntity() {
    return updatedEntity;
  }

  public void setUpdatedEntity(boolean newValue) {
    updatedEntity = newValue;
  }

  protected boolean mustUpdateValue(Object oldValue, Object newValue) {
    if ((oldValue == null) && (newValue == null)) {
      return false;
    }
    if ((oldValue == null) != (newValue == null)) {
      setUpdatedEntity(true);
      return true;
    }
    if (oldValue.equals(newValue)) {
      return false;
    }
    setUpdatedEntity(true);
    return true;
  }
}