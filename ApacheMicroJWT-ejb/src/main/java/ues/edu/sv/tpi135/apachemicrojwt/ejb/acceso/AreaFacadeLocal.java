/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ues.edu.sv.tpi135.apachemicrojwt.ejb.acceso;

import java.util.List;
import javax.ejb.Local;
import ues.edu.sv.tpi135.apachemicrojwt.lib.entidades.Area;

/**
 *
 * @author esperanza
 */
@Local
public interface AreaFacadeLocal {

    void create(Area area);

    void edit(Area area);

    void remove(Area area);

    Area find(Object id);

    List<Area> findAll();

    List<Area> findRange(int[] range);

    int count();
    
}