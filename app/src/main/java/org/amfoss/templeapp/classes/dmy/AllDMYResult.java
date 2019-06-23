package org.amfoss.templeapp.classes.dmy;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AllDMYResult {

  @SerializedName("dmypoojas")
  @Expose
  private List<DMYPooja> dmypoojas = null;

  /** No args constructor for use in serialization */
  public AllDMYResult() {}

  /** @param dmypoojas */
  public AllDMYResult(List<DMYPooja> dmypoojas) {
    super();
    this.dmypoojas = dmypoojas;
  }

  public List<DMYPooja> getDMYPoojas() {
    return dmypoojas;
  }

  public void setDMYPoojas(List<DMYPooja> dmypoojas) {
    this.dmypoojas = dmypoojas;
  }
}
