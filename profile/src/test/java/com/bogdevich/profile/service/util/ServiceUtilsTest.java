package com.bogdevich.profile.service.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceUtilsTest {

    @Test
    public void shouldCopyParameters() {
        List<A> aList = new ArrayList<>(4);
        aList.add(new A("test1", 1L, new byte[]{123, 122, 45}));
        aList.add(new A(null, null, null));
        aList.add(new A("", 0L, new byte[]{}));
        aList.add(new A(new String(new char[]{'w','0','r'}), -135151651651L, null));
        B source = new B(1L, aList, "source");

        B target_1 = new B();
        ServiceUtils.copyProperties(target_1, source, "id");
        Assert.assertEquals(source.getbString(), target_1.getbString());
        Assert.assertEquals(source.getbAList(), target_1.getbAList());
        Assert.assertNull(target_1.getId());
    }

    private static class A {
        private String aString;
        private Long aLong;
        private byte[] aByteArray;

        A(String aString, Long aLong, byte[] aByteArray) {
            this.aString = aString;
            this.aLong = aLong;
            this.aByteArray = aByteArray;
        }

        String getaString() {
            return aString;
        }

        Long getaLong() {
            return aLong;
        }

        byte[] getaByteArray() {
            return aByteArray;
        }

        public void setaString(String aString) {
            this.aString = aString;
        }

        public void setaLong(Long aLong) {
            this.aLong = aLong;
        }

        public void setaByteArray(byte[] aByteArray) {
            this.aByteArray = aByteArray;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            A a = (A) o;

            if (aString != null ? !aString.equals(a.aString) : a.aString != null) return false;
            if (aLong != null ? !aLong.equals(a.aLong) : a.aLong != null) return false;
            return Arrays.equals(aByteArray, a.aByteArray);
        }

        @Override
        public int hashCode() {
            int result = aString != null ? aString.hashCode() : 0;
            result = 31 * result + (aLong != null ? aLong.hashCode() : 0);
            result = 31 * result + Arrays.hashCode(aByteArray);
            return result;
        }
    }

    private static class B {
        private Long id;
        private List<A> bAList;
        private String bString;

        B() {
        }

        B(Long id, List<A> bAList, String bString) {
            this.id = id;
            this.bAList = bAList;
            this.bString = bString;
        }

        public Long getId() {
            return id;
        }

        public List<A> getbAList() {
            return bAList;
        }

        public String getbString() {
            return bString;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setbAList(List<A> bAList) {
            this.bAList = bAList;
        }

        public void setbString(String bString) {
            this.bString = bString;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            B b = (B) o;

            if (bAList != null ? !bAList.equals(b.bAList) : b.bAList != null) return false;
            return bString != null ? bString.equals(b.bString) : b.bString == null;
        }

        @Override
        public int hashCode() {
            int result = bAList != null ? bAList.hashCode() : 0;
            result = 31 * result + (bString != null ? bString.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "B{" +
                    "id=" + id +
                    ", bAList=" + bAList +
                    ", bString='" + bString + '\'' +
                    '}';
        }
    }
}
