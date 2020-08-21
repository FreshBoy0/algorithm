package com.doinb.cas;

import org.apache.commons.collections.list.SynchronizedList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedTest {

    public void hello() {

        synchronized (SynchronizedTest.class) {

            System.out.println("hello world...");
        }
    }

    public synchronized void helloLock() {

        System.out.println("helloLock...");
        SynchronizedList list = (SynchronizedList) Collections.synchronizedList(new ArrayList<>());
        list.add(1);
    }
}
