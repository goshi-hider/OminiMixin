package dev.goshi.omnimixin.api.sync;

public enum SyncedType {

    FLOAT {
        @Override
        public String encode(Object value) {
            return Float.toString((Float) value);
        }

        @Override
        public Object decode(String raw) {
            return Float.parseFloat(raw);
        }
    },

    DOUBLE {
        @Override
        public String encode(Object value) {
            return Double.toString((Double) value);
        }

        @Override
        public Object decode(String raw) {
            return Double.parseDouble(raw);
        }
    },

    INT {
        @Override
        public String encode(Object value) {
            return Integer.toString((Integer) value);
        }

        @Override
        public Object decode(String raw) {
            return Integer.parseInt(raw);
        }
    },

    BOOLEAN {
        @Override
        public String encode(Object value) {
            return Boolean.toString((Boolean) value);
        }

        @Override
        public Object decode(String raw) {
            return Boolean.parseBoolean(raw);
        }
    },

    STRING {
        @Override
        public String encode(Object value) {
            return (String) value;
        }

        @Override
        public Object decode(String raw) {
            return raw;
        }
    };

    public abstract String encode(Object value);

    public abstract Object decode(String raw);
}
