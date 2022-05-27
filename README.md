# Funcionamiento
Hay 4 recursos que están protegidas y podrán ser accedidas según el rol establecido en el recurso. Los roles son: 
- USER, MANAGER, ADMIN
# Acceso desde POSTMAN
- ROL: todos, sin excepción
  http://localhost:8080/security/all
- ROL: USER o MANAGER
  http://localhost:8080/security/user
- ROL: MANAGER
  http://localhost:8080/security/manager
- ROL: ADMIN
  http://localhost:8080/security/admin
# Credenciales por Basic Auth
- Usuarios: user, manager, admin
- Password: para todos será 12345