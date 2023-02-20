# spring-vault-config-example

# write : 
vault kv put secret/spring-vault-config-example javatechie.username=Basant javatechie.password=Basant_pwd

# read : 
vault kv get secret/spring-vault-config-example

# Delete: 
vault kv delete secret/spring-vault-config-example
