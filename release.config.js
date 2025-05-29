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
          { type: "build", section: "Dependencies", hidden: false },
          { type: "chore", section: "Other Changes", hidden: false },
          { type: "docs", section: "Documentation", hidden: false },
          { type: "style", section: "Styles", hidden: false },
          { type: "refactor", section: "Other Changes", hidden: false },
          { type: "perf", section: "Performance Improvements" },
          { type: "test", hidden: true },
          { type: "ci", section: "Other Changes", hidden: false },
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
