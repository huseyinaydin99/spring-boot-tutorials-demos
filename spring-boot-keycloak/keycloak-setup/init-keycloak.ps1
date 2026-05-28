# fix-keycloak-user.ps1
Write-Host "Keycloak Kullanıcı" -ForegroundColor Cyan

$baseUrl = "http://localhost:8180"

# =========================================================
# 🔥 0. KEYCLOAK HAZIR OLANA KADAR BEKLE
# =========================================================
Write-Host "Keycloak bekleniyor..." -ForegroundColor Yellow

do {
    try {
        Invoke-RestMethod "$baseUrl/realms/master" -Method Get | Out-Null
        Write-Host "Keycloak hazır!" -ForegroundColor Green
        break
    }
    catch {
        Start-Sleep -Seconds 3
    }
} while ($true)

# =========================================================
# 1. ADMIN TOKEN AL
# =========================================================
$tokenResponse = Invoke-RestMethod -Method Post `
    -Uri "$baseUrl/realms/master/protocol/openid-connect/token" `
    -ContentType "application/x-www-form-urlencoded" `
    -Body "client_id=admin-cli&username=admin&password=admin&grant_type=password"

$accessToken = $tokenResponse.access_token

# =========================================================
# 2. USER BUL (NULL SAFETY)
# =========================================================
$users = Invoke-RestMethod -Method Get `
    -Uri "$baseUrl/admin/realms/huseyinaydin99/users?username=admin" `
    -Headers @{ "Authorization" = "Bearer $accessToken" }

if (-not $users -or $users.Count -eq 0) {
    Write-Host "Kullanıcı bulunamadı, işlem durduruldu" -ForegroundColor Red
    exit
}

$userId = $users[0].id
Write-Host "Kullanıcı ID: $userId" -ForegroundColor Yellow

# =========================================================
# 3. USER SİL
# =========================================================
Write-Host "Kullanıcı siliniyor..." -ForegroundColor Yellow

Invoke-RestMethod -Method Delete `
    -Uri "$baseUrl/admin/realms/huseyinaydin99/users/$userId" `
    -Headers @{ "Authorization" = "Bearer $accessToken" }

Start-Sleep -Seconds 2

# =========================================================
# 4. USER YENİDEN OLUŞTUR
# =========================================================
$userBody = @{
    username = "admin"
    enabled = $true
    emailVerified = $true
    email = "admin@test.com"
    firstName = "Admin"
    lastName = "User"
    credentials = @(
        @{
            type = "password"
            value = "admin"
            temporary = $false
        }
    )
    requiredActions = @()
    attributes = @{
        emailVerified = "true"
    }
} | ConvertTo-Json -Depth 5

Write-Host "Kullanıcı oluşturuluyor..." -ForegroundColor Yellow

Invoke-RestMethod -Method Post `
    -Uri "$baseUrl/admin/realms/huseyinaydin99/users" `
    -Headers @{
        "Authorization" = "Bearer $accessToken"
        "Content-Type" = "application/json"
    } `
    -Body $userBody

Start-Sleep -Seconds 2

# =========================================================
# 5. YENİ USER ID AL
# =========================================================
$users = Invoke-RestMethod -Method Get `
    -Uri "$baseUrl/admin/realms/huseyinaydin99/users?username=admin" `
    -Headers @{ "Authorization" = "Bearer $accessToken" }

if (-not $users -or $users.Count -eq 0) {
    Write-Host "Yeni kullanıcı bulunamadı" -ForegroundColor Red
    exit
}

$newUserId = $users[0].id

# =========================================================
# 6. REQUIRED ACTIONS TEMİZLE
# =========================================================
Write-Host "Zorunlu aksiyonlar temizleniyor..." -ForegroundColor Yellow

$updateBody = @{
    requiredActions = @()
    emailVerified = $true
} | ConvertTo-Json

Invoke-RestMethod -Method Put `
    -Uri "$baseUrl/admin/realms/huseyinaydin99/users/$newUserId" `
    -Headers @{
        "Authorization" = "Bearer $accessToken"
        "Content-Type" = "application/json"
    } `
    -Body $updateBody

# =========================================================
# 7. PASSWORD RESET
# =========================================================
Write-Host "Şifre set ediliyor..." -ForegroundColor Yellow

$credentialBody = @{
    type = "password"
    value = "admin"
    temporary = $false
} | ConvertTo-Json

Invoke-RestMethod -Method Put `
    -Uri "$baseUrl/admin/realms/huseyinaydin99/users/$newUserId/reset-password" `
    -Headers @{
        "Authorization" = "Bearer $accessToken"
        "Content-Type" = "application/json"
    } `
    -Body $credentialBody

# =========================================================
# 8. TOKEN TEST
# =========================================================
Write-Host "`nToken test ediliyor..." -ForegroundColor Yellow

Start-Sleep -Seconds 2

try {
    $testResponse = Invoke-RestMethod -Method Post `
        -Uri "$baseUrl/realms/huseyinaydin99/protocol/openid-connect/token" `
        -ContentType "application/x-www-form-urlencoded" `
        -Body "client_id=spring-boot-keycloak&username=admin&password=admin&grant_type=password"

    Write-Host "BAŞARILI TOKEN ALINDI" -ForegroundColor Green
    Write-Host "Access Token: $($testResponse.access_token.Substring(0, 50))..." -ForegroundColor Cyan
}
catch {
    Write-Host "Token hatası: $_" -ForegroundColor Red
}