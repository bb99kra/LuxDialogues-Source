package org.aselstudios.luxdialogues.utils.charts;

import java.util.concurrent.Callable;
import org.aselstudios.luxdialogues.utils.json.JsonObjectBuilder;

public class SingleLineChart extends CustomChart {
   private final Callable<Integer> callable;

   public SingleLineChart(String chartId, Callable<Integer> callable) {
      super(chartId);
      this.callable = callable;
   }

   @Override
   protected JsonObjectBuilder.JsonObject getChartData() throws Exception {
      int value = this.callable.call();
      return value == 0 ? null : new JsonObjectBuilder().appendField("value", value).build();
   }
}
