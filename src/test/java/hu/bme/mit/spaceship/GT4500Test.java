package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore MockPF;
  private TorpedoStore MockSF;

  @BeforeEach
  public void init(){
    MockPF = mock(TorpedoStore.class);
    MockSF = mock(TorpedoStore.class);
    this.ship = new GT4500(MockPF,MockSF);
  }

  @Test
  public void fireTorpedo_Secondary_Single_Success(){
    // Arrange

    when(MockPF.isEmpty()).thenReturn(true);
    when(MockSF.isEmpty()).thenReturn(false);
    when(MockSF.fire(1)).thenReturn(true);




    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(MockSF, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Primary_Single_Success(){
    // Arrange

    when(MockPF.isEmpty()).thenReturn(false);
    when(MockSF.isEmpty()).thenReturn(true);
    when(MockPF.fire(1)).thenReturn(true);




    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(MockPF, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_Primary_Single_Fail(){
    // Arrange

    when(MockPF.isEmpty()).thenReturn(true);
    when(MockSF.isEmpty()).thenReturn(false);
    when(MockSF.fire(1)).thenReturn(false);




    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(MockPF, times(0)).fire(1);
    verify(MockSF,times(1)).fire(1 );

  }

  @Test
  public void fireTorpedo_Secondary_Single_Fail(){
    // Arrange

    when(MockPF.isEmpty()).thenReturn(false);
    when(MockSF.isEmpty()).thenReturn(true);
    when(MockPF.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(MockSF, times(0)).fire(1);
    verify(MockPF,times(1)).fire(1 );
  }


  @Test
  public void fireTorpedo_All_Fail(){
    // Arrange
    when(MockPF.isEmpty()).thenReturn(true);
    when(MockSF.isEmpty()).thenReturn(true);
    when(MockSF.fire(1)).thenReturn(false);
    when(MockSF.fire(1)).thenReturn(false);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(MockSF, times(0)).fire(1);
    verify(MockPF, times(0)).fire(1);
  }



  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    when(MockPF.isEmpty()).thenReturn(false);
    when(MockSF.isEmpty()).thenReturn(false);
    when(MockSF.fire(1)).thenReturn(true);
    when(MockSF.fire(1)).thenReturn(true);
    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertTrue(result);
    verify(MockSF, times(1)).fire(1);
    verify(MockPF, times(1)).fire(1);
  }

}
