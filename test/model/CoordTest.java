package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CoordTest {
  Coord coord1 = new Coord(2, 3);
  Coord coord2 = new Coord(-2, 20);
  Coord coord3 = new Coord(6, -4);

  @Test
  public void testGetRow() {
    assertEquals(coord1.getRow(), 2);
    assertEquals(coord2.getRow(), -2);
    assertEquals(coord3.getRow(), 6);
  }

  @Test
  public void testGetCol() {
    assertEquals(coord1.getCol(), 3);
    assertEquals(coord2.getCol(), 20);
    assertEquals(coord3.getCol(), -4);
  }
}