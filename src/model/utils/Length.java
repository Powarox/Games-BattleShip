package model.utils;

public enum Length{
        SHORT("Short", 8),
        NORMAL("Normal", 10),
        LONG("Long", 15);

        private String name;
        private int width;

        Length(String name, int width) {
            this.name = name;
            this.width = width;
        }

        public String getName(){
            return this.name;
        }

        public int getWidth(){
            return this.width;
        }

        public static Length getLenght(String obj) {
            obj = obj.toUpperCase();
            if ("SHORT".equals(obj)) {
                return Length.SHORT;
            }
            if("NORMAL".equals(obj)) {
                return Length.NORMAL;
            }
            if("LONG".equals(obj)) {
                return Length.LONG;
            }
            return null;
        }
}
