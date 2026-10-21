package com.xinfei.zookeeper.test;

import com.xinfei.behavior.BehaviorJarApplication;
import com.xinfei.common.zookeeper.sequence.Sequences;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BehaviorJarApplication.class)
public class ZkTest {

    @Autowired
    private Sequences sequences;

    @Test
    public void test1(){
        for(int i = 0;i<50;i++){
            Long aLong = sequences.sequenceApReadBehavior();
            System.out.println(aLong+"----------------");
        }

    }
}
