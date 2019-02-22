package com.timing.experience.base;

public class JavaGenerics {

    class Triple<A, B, C> {
        private A a;
        private B b;
        private C c;
        public Triple(A a, B b, C c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public void mehodOne() {
        int i = 0;
    }
}
