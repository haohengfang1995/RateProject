import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.wroldline.interview.Engine;
import com.wroldline.interview.WidgetMachine;

public class test {
	
	  @Mock
	    private Engine engine;

	    private WidgetMachine machine;

	    @BeforeEach
	    void setUp() {
	        // 初始化 Mockito 的 mock 物件
	        MockitoAnnotations.openMocks(this);
	        
	        // 初始化 WidgetMachine，並注入模擬的 Engine
	        machine = new WidgetMachine(engine);
	    }

	    @Test
	    void testProduceWidgetsWithOilFuel() {
	        // Arrange：模擬 engine 行為，使用油類燃料（OIL）
	        when(engine.isRunning()).thenReturn(true);  // 模擬 engine 啟動
	        when(engine.getCost()).thenReturn(8);      // 每批次8個
	        when(engine.getCostPerBatch()).thenReturn(9.0); // 每批成本9.0元
	        
	        // Act：生產 16 個 widget
	        double cost = machine.produceWidgets(16); 
	        
	        // Assert：根據成本計算
	        // 16個 widget 需要2批，每批成本9.0元，總共2 * 9.0 = 18.0元
	        assertEquals(18.0, cost);
	        
	        // 驗證 start() 和 stop() 方法是否正確調用
	        verify(engine).start();
	        verify(engine).stop();
	    }

	    @Test
	    void testProduceWidgetsWithSteamFuel() {
	        // Arrange：模擬 engine 行為，使用蒸汽類燃料（STEAM）
	        when(engine.isRunning()).thenReturn(true);  // 模擬 engine 啟動
	        when(engine.getCost()).thenReturn(2);       // 每批次2個
	        when(engine.getCostPerBatch()).thenReturn(4.35); // 每批成本4.35元
	        
	        // Act：生產 10 個 widget
	        double cost = machine.produceWidgets(10); 
	        
	        // Assert：根據成本計算
	        // 10個 widget 需要5批，每批成本4.35元，總共5 * 4.35 = 21.75元
	        assertEquals(21.75, cost);
	        
	        // 驗證 start() 和 stop() 方法是否正確調用
	        verify(engine).start();
	        verify(engine).stop();
	    }

	    @Test
	    void testEngineNotRunning() {
	        // Arrange：模擬 engine 尚未啟動的情況
	        when(engine.isRunning()).thenReturn(false);  // 模擬 engine 沒有啟動
	        
	        // Act：嘗試生產 widget
	        double cost = machine.produceWidgets(10); 
	        
	        // Assert：當 engine 沒有運行時，成本應該為 0
	        assertEquals(0.0, cost);
	        
	        // 驗證 start() 和 stop() 方法仍然被調用
	        verify(engine).start();
	        verify(engine).stop();
	    }

	    @Test
	    void testEngineStartFailure() {
	        // Arrange：模擬無法啟動 engine
	        when(engine.isRunning()).thenReturn(false);  // 模擬 engine 啟動失敗
	        
	        // Act & Assert：當 engine 無法啟動時，拋出異常
	        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> {
	            machine.produceWidgets(10);
	        });
	        
	        assertEquals("Not able to start engine.", thrown.getMessage());
	    }
}
