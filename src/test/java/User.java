public class User {
        private String name;
        private String addr;
        private Integer age;

        public User(String name, String addr, Integer age) {
                this.name = name;
                this.addr = addr;
                this.age = age;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                User user = (User) o;

                if (!name.equals(user.name)) return false;
                if (!addr.equals(user.addr)) return false;
                return age.equals(user.age);
        }

        @Override
        public int hashCode() {
                int result = name.hashCode();
                result = 31 * result + addr.hashCode();
                result = 31 * result + age.hashCode();
                return result;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAddr() {
                return addr;
        }

        public void setAddr(String addr) {
                this.addr = addr;
        }

        public Integer getAge() {
                return age;
        }

        public void setAge(Integer age) {
                this.age = age;
        }

        @Override
        public String toString() {
                return "User{" +
                        "name='" + name + '\'' +
                        ", addr='" + addr + '\'' +
                        ", age=" + age +
                        '}';
        }
}
