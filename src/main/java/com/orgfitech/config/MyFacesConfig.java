/***************************************************************************f******************u************zz*******y**
 * File: MyFacesConfig.java
 * Course materials (20W) CST 8277
 * 
 * @author (original) Mike Norman
 * 
 */
package com.orgfitech.config;

import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
@FacesConfig(
    // JSF 2.3 is configured 'out-of-the-box' to be backwards-compatible with 2.2
    // so if you want 2.3 features, you must *explicitly* configure it with the
    // @FacesConfig annotation and Version.JSF_2_3 constant
    version = Version.JSF_2_3
)
public class MyFacesConfig  {
}