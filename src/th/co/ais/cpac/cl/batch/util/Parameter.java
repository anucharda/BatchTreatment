/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.ais.cpac.cl.batch.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Optimus
 */
public class Parameter {

  private static final String nameFirst[] = {"sd1", "sd2", "sd3", "rc", "dt"};
  private static final String valueFirst[] = {"suspend1", "suspend2", "suspend3", "reconnect", "terminate"};

  public static void main(String[] args) {
    String fileName = "D:\\Work\\Ais\\_document\\_temp\\";

    fileName += "cl_ca_info.txt";

    List<String> list = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      StringBuilder p2 = new StringBuilder();
      StringBuilder p3 = new StringBuilder();
      String line;
      String listColumn = "";
      while ((line = br.readLine()) != null) {
        //System.out.println(line)
        line = line.replace("\t", " ");
        String[] string = line.split(" ");
        String name = null;
        String type = null;
        String column = null;
        for (int i = 0; i < string.length; i++) {
          String temp = string[i].trim();
          if (temp.isEmpty()) {
            continue;
          }
          if (name == null) {
            name = temp;
            column = temp.toUpperCase();
            listColumn += temp.toUpperCase() + ",";
            while (name.contains("_")) {
              int index = name.indexOf("_");
              String regex = name.substring(index, index + 2);
              String replace = regex.replace("_", "").toUpperCase();
              name = name.replaceAll(regex, replace);
            }
            name = name.replace("No", "Number");
            
            for (int iii = 0; iii < nameFirst.length; iii++) {
              if (name.startsWith(nameFirst[iii])) {
                name.replace(nameFirst[iii], valueFirst[iii]);
              }
            }

            //System.out.println("--------------- " +name);            
            continue;
          }
          if (type == null) {
            type = temp;
          } else {
            type = type + " " + temp;
          }
        }

        if (name != null && type != null) {
          //System.out.println(name + " -> " + type);

          String value = "";
          String insertValue = "";

          String p1 = "private ";
          type = type.toLowerCase();
          
          if (type.startsWith("varchar") || type.startsWith("univarchar")) {
            p1 += "String ";
            value = "resultSet.getString(\"" + column + "\")";
            insertValue = "genString";

          } else if (type.startsWith("unsigned bigint") || type.startsWith("decimal") || type.startsWith("int") || type.startsWith("smallint")) {
            p1 += "BigDecimal ";
            value = "resultSet.getBigDecimal(\"" + column + "\")";
            insertValue = "genNumber";
          } else if (type.startsWith("date")) {
            p1 += "Date ";
            value = "toDate(resultSet.getTimestamp(\"" + column + "\"), null)";
            insertValue = "genDateTime";
          } else if (type.startsWith("char(1)")) {
            p1 += "Character ";
            value = "toCharcter( resultSet.getString(\"" + column + "\") , 'N')";
            insertValue = "genString";
          } else if (type.startsWith("char")) {
            p1 += "String ";
            value = "resultSet.getString(\"" + column + "\")";
            insertValue = "genString";
          } else {
            p1 += "nooooooooooooooooooooo";
          }
          p1 += name;
          p1 += ";";
          System.out.println(p1);

          String first = name.substring(0, 1);
          String second = name.substring(1);
          //temp.setBatchService(toCharcter( resultSet.getString("") , 'N'));
          //temp.setBatchTypeName(resultSet.getString("BATCH_TYPE_NAME"));

          p2.append(MessageFormat.format("temp.set{0}({1});", (first.toUpperCase() + second), value));
          p2.append("\n");
          p3.append(MessageFormat.format("{0}(\"{1}\",request.get{2}(),null,column,value,false);", insertValue, column, (first.toUpperCase() + second)));
          p3.append("\n");
        }

      }
      System.out.println(p2);
      System.out.println(p3);
      System.out.println(listColumn);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
