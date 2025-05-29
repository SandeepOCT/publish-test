module.exports = {
  branches: ["main"],
  plugins: [
    [
      "@semantic-release/commit-analyzer",
      {
        preset: "conventionalcommits", // using the conventionalcommits preset, default is angular
      },
    ],
    [
      "@semantic-release/changelog",
      {
        changelogFile: "CHANGELOG.md",
      },
    ],
    "@semantic-release/release-notes-generator",
    {
      preset: "conventionalcommits",
      presetConfig: {
        types: [
          { type: "feat", section: "Features" },
          { type: "fix", section: "Bug Fixes" },
          { type: "build", section: "Dependencies" },
          { type: "chore", section: "Other Changes" },
          { type: "docs", section: "Documentation" },
          { type: "style", section: "Styles" },
          { type: "refactor", section: "Other Changes" },
          { type: "perf", section: "Performance Improvements" },
          { type: "test", hidden: true },
          { type: "ci", section: "Continuous Integration" },
        ],
      },
    },
    "@semantic-release/npm",
    [
      "@semantic-release/git",
      {
        assets: ["CHANGELOG.md", "package.json", "package-lock.json"],
        message:
          "chore(release): :rocket: ${nextRelease.version} [skip ci]\n\n${nextRelease.notes}",
      },
    ],
    "@semantic-release/github", // test github release
  ],
};
