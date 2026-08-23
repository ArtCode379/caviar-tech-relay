Fix the Android project at /tmp/caviar-tech-relay so the failing quality-fix-1 step passes.

Use these orchestrator instructions: /home/codex-agent/codex-app-agent/AGENTS.md
Screen spec: /home/codex-agent/codex-app-agent/screens-shop.md
Do not push to GitHub, do not update Asana, and do not send Slack.
Fix formatting failures by expanding the affected Kotlin code; do not suppress or bypass the formatting checks.

Recent failure log:
```text
=== QUALITY CHECK: /tmp/caviar-tech-relay ===

WARN: Only 1 commit(s) — final implementation commit may not exist yet
  OK: Repository: 12 entries
  PLACEHOLDER-LIKE: app/src/main/res/drawable/product_3.jpg (colors=251, entropy=0.688386)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/product_4.jpg (colors=12118, entropy=0.842117)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/product_5.jpg (colors=8419, entropy=0.763877)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/product_6.jpg (colors=12375, entropy=0.709745)
  OK: 12 images
  OK: All images valid
FAIL: 4 placeholder-like drawable image(s); use real photos or filesystem-backed imagegen output, not local generated placeholders
  OK: No empty onClick
  OK: No obvious no-op onClick handlers
  OK: icon.png (201713B, 512x512, rounded opaque canvas, transparent corners)
FAIL: Manifest references .SkeletonApplication but class not found — CRASH
  OK: HomeScreen.kt: 185 lines
  OK: No project-local agent instruction files
  OK: dynamicColor not enabled
  OK: Google Fonts dependency found
FAIL: font_certs.xml missing
  OK: HorizontalPager used
  OK: No drawable resources detected in AsyncImage lines
  OK: Kotlin source formatting

=== RESULT: 3 error(s) ===
FIX ALL ISSUES BEFORE PUSH

```
